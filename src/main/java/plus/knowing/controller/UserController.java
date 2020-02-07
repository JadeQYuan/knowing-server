package plus.knowing.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.knowing.annotation.Role;
import plus.knowing.vo.sys.UserVO;

@RestController
@RequestMapping(path = "/users")
@Validated
public class UserController {

    @Role
    @GetMapping(path = "")
    public UserVO getInfo(@RequestAttribute UserVO user) {
        return user;
    }
}
