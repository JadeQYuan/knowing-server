package plus.knowing.aspect;

import lombok.Getter;

@Getter
public class UnifiedResponseBody<T> {

    private Integer code;

    private String message;

    private T data;

    private UnifiedResponseBody() {}

    private UnifiedResponseBody(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    static <T> UnifiedResponseBody<T> buildSuccessResponse(T data) {
        return new UnifiedResponseBody<>(ResponseEnum.SUCCESS, data);
    }

    public static UnifiedResponseBody<String> buildFailureResponse(String message) {
        return new UnifiedResponseBody<>(ResponseEnum.FAILURE, message);
    }

    public static UnifiedResponseBody<String> buildErrorResponse() {
        return new UnifiedResponseBody<>(ResponseEnum.ERROR, null);
    }

    @Getter
    enum  ResponseEnum{
        SUCCESS(200, "成功"),
        FAILURE(300, "失败"),
        ERROR(500, "服务异常"),
        ;

        private Integer code;

        private String message;

        ResponseEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
