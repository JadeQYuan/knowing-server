package plus.knowing.config.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "qq")
public class QQConfigProp {

    private String appId;

    private String appKey;

    private String redirectUri;

    private String authorizeUrl;

    private String accessTokenUrl;

    private String getOpenIdUrl;

    private String getUserInfoUrl;
}
