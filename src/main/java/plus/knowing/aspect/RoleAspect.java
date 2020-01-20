package plus.knowing.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.knowing.annotation.Role;
import plus.knowing.constant.RoleEnum;
import plus.knowing.service.IAuthService;
import plus.knowing.vo.sys.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class RoleAspect {

    private static final String TOKEN = "X-Token";

    private static final String LOGIN_USER_KEY = "user";

    @Autowired
    private IAuthService iAuthService;

    @Before("execution(public * plus.knowing.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        // 登陆权限验证
        log.info(String.format("request[Method: '%s', RequestURI: '%s', RemoteAddr: '%s']", request.getMethod(),
                request.getRequestURI(), request.getRemoteAddr()));
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            return;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Role roleAnnotation = method.getAnnotation(Role.class);
        if (Objects.nonNull(roleAnnotation)) {
            validPermission(request, roleAnnotation);
        }
    }

    private void validPermission(HttpServletRequest request, Role roleAnnotation) {
        String token = request.getHeader(TOKEN);
        UserVO userVO = null;
        if (StringUtils.hasText(token)){ // token获取登陆用户
            userVO = iAuthService.getUserByToken(token);
        }
        if (Objects.isNull(userVO)){ // 登陆用户为空，未登录
            throw new RuntimeException("未登录");
        }
        request.setAttribute(LOGIN_USER_KEY, userVO);
        // 管理员可进行所有操作
        if (userVO.getRoles().contains(RoleEnum.Admin)) {
            return;
        }
        List<RoleEnum> roleEnumList = Arrays.stream(roleAnnotation.value()).collect(Collectors.toList());
        if (!CollectionUtils.containsAny(roleEnumList, userVO.getRoles())) {
            throw new RuntimeException("权限不足，无法操作！");
        }
    }
}
