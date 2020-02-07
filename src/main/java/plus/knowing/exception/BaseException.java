package plus.knowing.exception;

import lombok.Getter;
import plus.knowing.exception.data.ExceptionEnum;

@Getter
public class BaseException extends RuntimeException {

    private Integer code;

    private String message;

    public BaseException(ExceptionEnum exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    public BaseException(ExceptionEnum exception, String message) {
        this.code = exception.getCode();
        this.message = message;
    }
}
