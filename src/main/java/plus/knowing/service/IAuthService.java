package plus.knowing.service;

import plus.knowing.constant.AuthPlateFormEnum;
import plus.knowing.vo.sys.UserVO;
import plus.knowing.vo.sys.auth.AuthVO;

public interface IAuthService {

    /**
     * 获取登录url
     * @param authPlateForm
     * @return
     */
    String getLoginUrl(AuthPlateFormEnum authPlateForm);

    /**
     * 登录
     * @param authVO
     * @return
     */
    String login(AuthVO authVO);

    /**
     * 根据token获取登录用户
     * @param token
     * @return
     */
    UserVO getUserByToken(String token);
}
