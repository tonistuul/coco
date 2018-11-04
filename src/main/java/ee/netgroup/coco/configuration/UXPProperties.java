package ee.netgroup.coco.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "uxp")
public class UXPProperties {
  private String securityServer;
  private String instance;

  private XRoadClientProps client = new XRoadClientProps();

  @Data
  public static class XRoadClientProps {
    private String memberClass;
    private String memberCode;
    private String subsystemCode;
  }
}