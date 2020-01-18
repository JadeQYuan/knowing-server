package plus.knowing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import plus.knowing.config.prop.GitHubConfigProp;
import plus.knowing.config.prop.QQConfigProp;
import plus.knowing.constant.AuthPlateFormEnum;
import plus.knowing.dao.SysUserDao;
import plus.knowing.dao.SysUserOAuthDao;
import plus.knowing.entity.SysUser;
import plus.knowing.entity.SysUserOAuth;
import plus.knowing.service.IAuthService;
import plus.knowing.util.JsonUtils;
import plus.knowing.vo.auth.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QQConfigProp qqConfigProp;

    @Autowired
    private GitHubConfigProp gitHubConfigProp;

    @Autowired
    private SysUserOAuthDao sysUserOAuthDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public String getLoginUrl(AuthPlateFormEnum authPlateForm) {
        switch (authPlateForm) {
            case QQ:
                return String.format(qqConfigProp.getAuthorizeUrl(), authPlateForm.name());
            case GitHub:
                return String.format(gitHubConfigProp.getAuthorizeUrl(), authPlateForm.name());
            default:
                throw new RuntimeException("登录平台不支持！");
        }
    }

    @Override
    @Transactional
    public String login(AuthVO authVO) {
        SysUserOAuth userOAuth = new SysUserOAuth();
        userOAuth.setPlatform(authVO.getState().name());
        SysUser sysUser;
        switch (authVO.getState()) {
            case QQ: {
                String accessTokenStr = restTemplate.getForObject(String.format(qqConfigProp.getAccessTokenUrl(), authVO.getCode()), String.class);
                QQAccessToken accessToken = an(accessTokenStr);
                String openIdStr = restTemplate.getForObject(String.format(qqConfigProp.getGetOpenIdUrl(), accessToken.getAccess_token()), String.class);
                QQOpenId openId = JsonUtils.read(openIdStr.replace("callback( ", "").replace(" )", ""), QQOpenId.class);;
                userOAuth.setOpenId(openId.getOpenid());
                SysUserOAuth oAuth = sysUserOAuthDao.selectOne(new QueryWrapper<>(userOAuth));
                if (Objects.isNull(oAuth)) {
                    String userInfoStr = restTemplate.getForObject(String.format(qqConfigProp.getGetUserInfoUrl(), accessToken.getAccess_token(), openId.getOpenid()), String.class);
                    QQUserInfo userInfo = JsonUtils.read(userInfoStr.replace("callback( ", "").replace(" )", ""), QQUserInfo.class);;
                    sysUser = new SysUser();
                    sysUser.setNickname(userInfo.getNickname());
                    sysUser.setAvatarUrl(userInfo.getFigureurl_qq_1());
                    sysUser.setCreateTime(LocalDateTime.now());
                    sysUserDao.insert(sysUser);
                    userOAuth.setUserId(sysUser.getId());
                    sysUserOAuthDao.insert(userOAuth);
                } else {
                    sysUser = sysUserDao.selectById(oAuth.getUserId());
                }
                break;
            }
            case GitHub: {
                GitHubAccessToken accessToken = restTemplate.getForObject(String.format(gitHubConfigProp.getAccessTokenUrl(), authVO.getCode()), GitHubAccessToken.class);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Authorization", String.format("token %s", accessToken.getAccess_token()));
                HttpEntity httpEntity = new HttpEntity(httpHeaders);
                GitHubUserInfo userInfo = restTemplate.exchange(gitHubConfigProp.getGetOpenIdUrl(), HttpMethod.GET, httpEntity, GitHubUserInfo.class).getBody();
                userOAuth.setOpenId(userInfo.getNode_id());
                SysUserOAuth oAuth = sysUserOAuthDao.selectOne(new QueryWrapper<>(userOAuth));
                if (Objects.isNull(oAuth)) {
                    sysUser = new SysUser();
                    sysUser.setNickname(userInfo.getName());
                    sysUser.setAvatarUrl(userInfo.getAvatar_url());
                    sysUser.setCreateTime(LocalDateTime.now());
                    sysUserDao.insert(sysUser);
                    userOAuth.setUserId(sysUser.getId());
                    sysUserOAuthDao.insert(userOAuth);
                } else {
                    sysUser = sysUserDao.selectById(oAuth.getUserId());
                }
                break;
            }
            default:
                throw new RuntimeException("登录平台不支持！");
        }
        return Arrays.toString(DigestUtils.md5Digest((sysUser.getId() + "&" + authVO.getState() + "&" + System.currentTimeMillis()).getBytes()));
    }

    private QQAccessToken an(String str) {
        QQAccessToken token = new QQAccessToken();
        for (String s : str.split("&")) {
            String[] split = s.split("=");
            switch (split[0]) {
                case "access_token" :
                    token.setAccess_token(split[1]);
                    break;
                case "expires_in" :
                    token.setExpires_in(Long.parseLong(split[1]));
                    break;
                case "refresh_token" :
                    token.setRefresh_token(split[1]);
                    break;
            }
        }
        return token;
    }
}
