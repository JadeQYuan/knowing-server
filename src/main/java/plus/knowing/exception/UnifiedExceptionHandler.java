package plus.knowing.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import plus.knowing.aspect.UnifiedResponseBody;

@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public UnifiedResponseBody<String> handleException(Exception ex) {
        if (ex instanceof DBException) {
            return UnifiedResponseBody.buildFailureResponse(String.format("【数据异常 : %s】", "ex.getMessage()"));
        } else if (ex instanceof BizException) {
            return UnifiedResponseBody.buildFailureResponse(String.format("【业务异常 : %s】", "ex.getMessage()"));
        }
        return UnifiedResponseBody.buildErrorResponse();
    }
}
