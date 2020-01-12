package plus.knowing.service;

import plus.knowing.constant.AuthPlateFormEnum;
import plus.knowing.vo.auth.AuthVO;

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
}
