package ee.netgroup.coco.repository;

import ee.netgroup.coco.configuration.UXPProperties;
import ee.netgroup.coco.configuration.UXPServiceProviderProperties;

import static java.lang.String.format;

public abstract class UXPRepository {

  private UXPProperties uxpProperties;

  public UXPRepository(UXPProperties uxpProperties) {
    this.uxpProperties = uxpProperties;
  }

  String getUrl(String service, int version, String pathSegment) {
    String result = format("%s/%s/%s/%s",
      uxpProperties.getSecurityServer(),
      uxpProperties.getClient().getMemberClass(),
      uxpProperties.getClient().getMemberCode(),
      uxpProperties.getClient().getSubsystemCode()
    );

    if (pathSegment != null) {
      result += format("/%s", pathSegment);
    }

    result += format("?xRoadInstance=%s&memberClass=%s&memberCode=%s&subsystemCode=%s&serviceCode=%s&serviceVersion=%s",
      uxpProperties.getInstance(),
      getServiceProviderProperties().getMemberClass(),
      getServiceProviderProperties().getMemberCode(),
      getServiceProviderProperties().getSubsystemCode(),
      service,
      version
    );

    return result;
  }

  abstract UXPServiceProviderProperties getServiceProviderProperties();
}