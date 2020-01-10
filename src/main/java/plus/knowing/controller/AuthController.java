package plus.knowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.knowing.service.IAuthService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private IAuthService iAuthService;

    @GetMapping(path = "/qq")
    public String getQQLoginUrl(HttpServletRequest request) {
        return iAuthService.getQQLoginUrl(request);
    }

    @PostMapping(path = "qq")
    public void qqLogin(String code, String state) {
        iAuthService.qqLogin(code, state);
    }
}
