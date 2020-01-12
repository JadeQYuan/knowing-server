package plus.knowing.constant;

import lombok.Getter;

@Getter
public enum AuthPlateFormEnum {
    QQ ("qq"),
    GitHub("gitHub"),
    ;

    private String path;

    AuthPlateFormEnum(String path) {
        this.path = path;
    }
}
