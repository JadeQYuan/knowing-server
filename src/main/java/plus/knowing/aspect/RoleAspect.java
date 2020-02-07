package plus.knowing.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.knowing.annotation.Role;
import plus.knowing.constant.RoleEnum;
import plus.knowing.controller.advice.AuthInterceptor;
import plus.knowing.vo.sys.UserVO;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class RoleAspect {

    @Before("@annotation(plus.knowing.annotation.Role)")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        UserVO userVO = (UserVO) sra.getRequest().getAttribute(AuthInterceptor.LOGIN_USER_KEY);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Role roleAnnotation = method.getAnnotation(Role.class);
        if (Objects.nonNull(roleAnnotation)) {
            validPermission(userVO, roleAnnotation);
        }
    }

    private void validPermission(UserVO userVO, Role roleAnnotation) {
        if (Objects.isNull(userVO)) {
            throw new RuntimeException("未登录！");
        }
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
