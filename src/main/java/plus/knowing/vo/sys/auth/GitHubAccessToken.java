package plus.knowing.vo.sys.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitHubAccessToken {

    private String token_type;

    private String scope;

    private String access_token;
}
