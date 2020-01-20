package plus.knowing.vo.sys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.constant.RoleEnum;
import plus.knowing.entity.SysUser;
import plus.knowing.util.JsonUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Long id;

    private String nickname;

    private String avatarUrl;

    private String email;

    private List<RoleEnum> roles;

    public UserVO(SysUser user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.avatarUrl = user.getAvatarUrl();
        this.email = user.getEmail();
        this.roles = JsonUtils.read(user.getRoles(), List.class, RoleEnum.class);
    }
}
