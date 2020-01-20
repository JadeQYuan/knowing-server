package plus.knowing.vo.sys.auth;

import lombok.Getter;
import lombok.Setter;
import plus.knowing.constant.AuthPlateFormEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthVO {

    @NotBlank(message = "OAuth Code不能为空")
    private String code;

    @NotNull(message = "state不能为空")
    private AuthPlateFormEnum state;
}
