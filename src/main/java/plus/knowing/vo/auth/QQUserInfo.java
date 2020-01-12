package plus.knowing.vo.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QQUserInfo {

    private String ret;

    private String msg;

    /**
     * 用户在QQ空间的昵称
     */
    private String nickname;

    /**
     * 大小为30×30像素的QQ空间头像URL
     */
    private String figureurl;

    /**
     * 大小为50×50像素的QQ空间头像URL
     */
    private String figureurl_1;

    /**
     * 大小为100×100像素的QQ空间头像URL
     */
    private String figureurl_2;

    /**
     * 大小为40×40像素的QQ头像URL
     */
    private String figureurl_qq_1;

    /**
     * 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
     */
    private String figureurl_qq_2;

    /**
     * 如果获取不到则默认返回"男"
     */
    private String gender;
}
