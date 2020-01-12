package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.knowing.constant.AuthPlateFormEnum;
import plus.knowing.service.IAuthService;
import plus.knowing.vo.auth.AuthVO;

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
    public void login(@Validated @RequestBody AuthVO authVO) {
        iAuthService.login(authVO);
    }
}
