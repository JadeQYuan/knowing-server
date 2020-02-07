package plus.knowing.vo.sys.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QQAccessToken {

    private String access_token;

    private Long expires_in;

    private String refresh_token;
}
