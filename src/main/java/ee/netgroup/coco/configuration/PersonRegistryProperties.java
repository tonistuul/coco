package ee.netgroup.coco.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@ConfigurationProperties(prefix = "person-registry")
public class PersonRegistryProperties implements UXPServiceProviderProperties {
  private String memberClass;
  private String memberCode;
  private String subsystemCode;

  @Override
  public String getMemberClass() {
    return memberClass;
  }

  @Override
  public String getMemberCode() {
    return memberCode;
  }

  @Override
  public String getSubsystemCode() {
    return subsystemCode;
  }
}