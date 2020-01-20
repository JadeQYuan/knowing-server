package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.knowing.annotation.Role;
import plus.knowing.service.IAuthService;
import plus.knowing.vo.sys.UserVO;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(path = "/users")
@Validated
public class UserController {

    @Autowired
    private IAuthService iAuthService;

    @Role
    @GetMapping(path = "")
    public UserVO getInfo(@NotBlank String token) {
        return iAuthService.getUserByToken(token);
    }
}
