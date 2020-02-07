package plus.knowing.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import plus.knowing.service.IAuthService;
import plus.knowing.vo.sys.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "Token";

    public static final String LOGIN_USER_KEY = "user";

    @Autowired
    private IAuthService iAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 登陆权限验证
        log.info(String.format("request[Method: '%s', RequestURI: '%s', RemoteAddr: '%s']", request.getMethod(),
                request.getRequestURI(), request.getRemoteAddr()));
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader(TOKEN);
        UserVO userVO = null;
        if (StringUtils.hasText(token)){ // token获取登陆用户
            userVO = iAuthService.getUserByToken(token);
        }
        request.setAttribute(LOGIN_USER_KEY, userVO);
        return true;
    }
}
