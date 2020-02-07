package plus.knowing.exception.data;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    InsufficientPermissions
    ;
    private Integer code;

    private String message;

}
