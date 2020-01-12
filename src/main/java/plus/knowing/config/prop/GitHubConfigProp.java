package plus.knowing.config.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "git-hub")
public class GitHubConfigProp {

    private String appId;

    private String appKey;

    private String redirectUri;

    private String authorizeUrl;

    private String accessTokenUrl;

    private String getOpenIdUrl;
}
