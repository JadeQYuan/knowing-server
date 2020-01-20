package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.knowing.constant.AuthPlateFormEnum;
import plus.knowing.service.IAuthService;
import plus.knowing.vo.sys.auth.AuthVO;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private IAuthService iAuthService;

    @GetMapping(path = "/{authPlateForm}")
    public String getLoginUrl(@PathVariable AuthPlateFormEnum authPlateForm) {
        return iAuthService.getLoginUrl(authPlateForm);
    }

    @PostMapping(path = "")
    public String login(@Validated @RequestBody AuthVO authVO) {
        return iAuthService.login(authVO);
    }
}
