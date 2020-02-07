package plus.knowing.exception.data;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    Success(200, "成功"),

    // 认证异常
    NotLogged(30101, "未登录"),
    LoginExpired(30102, "登录已过期"),

    // 权限异常
    InsufficientPermissions(30201, "权限不足，无法操作"),

    // 数据异常
    DataNotExist(30301, "数据不存在"),
    DataExists(30302, "数据已存在"),

    // 业务异常

    Error(500, "服务异常");
 ;
    private Integer code;

    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
