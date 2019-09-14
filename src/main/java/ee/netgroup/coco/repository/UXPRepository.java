package ee.netgroup.coco.repository;

import ee.netgroup.coco.configuration.UXPProperties;
import ee.netgroup.coco.configuration.UXPServiceProviderProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static java.lang.String.format;

public abstract class UXPRepository {

  private UXPProperties uxpProperties;
  private HttpHeaders httpHeaders;

  public UXPRepository(UXPProperties uxpProperties) {
    this.uxpProperties = uxpProperties;
  }

  String getUrl(String service, int version, String pathSegment) {
    return format("%s/%s?xRoadInstance=%s&memberClass=%s&memberCode=%s&subsystemCode=%s&serviceCode=%s&serviceVersion=%s",
      uxpProperties.getSecurityServer(),
      pathSegment != null ? pathSegment : "",
      uxpProperties.getInstance(),
      getServiceProviderProperties().getMemberClass(),
      getServiceProviderProperties().getMemberCode(),
      getServiceProviderProperties().getSubsystemCode(),
      service,
      version
    );
  }

  HttpEntity getHeadersEntity() {
    return new HttpEntity(getHeaders());
  }

  HttpHeaders getHeaders() {
    if (this.httpHeaders == null) {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Uxp-Client", String.format("%s/%s/%s/%s",
        uxpProperties.getInstance(),
        getServiceProviderProperties().getMemberClass(),
        getServiceProviderProperties().getMemberCode(),
        getServiceProviderProperties().getSubsystemCode()));
      this.httpHeaders = HttpHeaders.readOnlyHttpHeaders(headers);
    }
    return this.httpHeaders;
  }

  abstract UXPServiceProviderProperties getServiceProviderProperties();
}