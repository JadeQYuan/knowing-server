package plus.knowing.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import plus.knowing.exception.BizException;
import plus.knowing.exception.DBException;
import plus.knowing.util.JsonUtils;

@RestControllerAdvice
@Slf4j
public class UnifiedResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof UnifiedResponseBody) {
            return body;
        }
        if (body instanceof String) {
            return JsonUtils.writeValueAsString(UnifiedResponseBody.buildSuccessResponse(body));
        }
        return UnifiedResponseBody.buildSuccessResponse(body);
    }

    @ExceptionHandler(value = Exception.class)
    public UnifiedResponseBody<String> handleException(Exception e) {
        if (e instanceof DBException) {
            return UnifiedResponseBody.buildFailureResponse(String.format("【数据异常 : %s】", e.getMessage()));
        } else if (e instanceof BizException) {
            return UnifiedResponseBody.buildFailureResponse(String.format("【业务异常 : %s】", e.getMessage()));
        }
        log.error("【系统异常】", e);
        return UnifiedResponseBody.buildErrorResponse();
    }
}
