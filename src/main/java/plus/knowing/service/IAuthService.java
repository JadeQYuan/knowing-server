package plus.knowing.service;

import javax.servlet.http.HttpServletRequest;

public interface IAuthService {

    /**
     * 获取QQ登录url
     * @return
     * @param request
     */
    String getQQLoginUrl(HttpServletRequest request);

    /**
     * QQ登录
     * @param code
     * @param state
     */
    void qqLogin(String code, String state);
}
