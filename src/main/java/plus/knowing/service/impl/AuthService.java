package plus.knowing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import plus.knowing.config.prop.QQConfigProp;
import plus.knowing.service.IAuthService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QQConfigProp qqConfigProp;

    @Override
    public String getQQLoginUrl(HttpServletRequest request) {
        return String.format(qqConfigProp.getAuthorizeUrl(), "123");
    }

    @Override
    public void qqLogin(String code, String state) {
        Map accessToken = restTemplate.getForObject(String.format(qqConfigProp.getAccessTokenUrl(), code), Map.class);
        Map openId = restTemplate.getForObject(String.format(qqConfigProp.getGetOpenIdUrl(), accessToken.get("access_token")), Map.class);
        System.out.println("openId: " + openId.get("openid"));
        Map userInfo = restTemplate.getForObject(String.format(qqConfigProp.getGetUserInfoUrl(), accessToken.get("access_token"), openId.get("openid")), Map.class);
        System.out.println("nickname" + userInfo.get("nickname"));
    }
}
