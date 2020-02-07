package plus.knowing.aspect;

import lombok.Getter;
import plus.knowing.exception.BaseException;
import plus.knowing.exception.data.ExceptionEnum;

@Getter
public class UnifiedResponseBody<T> {

    private Integer code;

    private String message;

    private T data;

    private UnifiedResponseBody() {}

    private UnifiedResponseBody(ExceptionEnum exceptionEnum, T data) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.data = data;
    }

    private UnifiedResponseBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    static <T> UnifiedResponseBody<T> buildSuccessResponse(T data) {
        return new UnifiedResponseBody<>(ExceptionEnum.Success, data);
    }

    public static UnifiedResponseBody<String> buildFailureResponse(BaseException e) {
        return new UnifiedResponseBody<>(e.getCode(), e.getMessage());
    }

    public static UnifiedResponseBody<String> buildErrorResponse() {
        return new UnifiedResponseBody<>(ExceptionEnum.Error, null);
    }
}
