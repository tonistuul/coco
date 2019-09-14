package ee.netgroup.coco.repository;

import ee.netgroup.coco.configuration.BusinessRegistryProperties;
import ee.netgroup.coco.configuration.UXPProperties;
import ee.netgroup.coco.configuration.UXPServiceProviderProperties;
import ee.netgroup.coco.dto.LegalEntityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.springframework.http.HttpMethod.GET;

@Repository
public class UXPLegalEntityRepository extends UXPRepository {
  private static final String SERVICE_COMPANIES = "companies";
  private static final String SERVICE_RESTRICTION = "business-restriction";
  private BusinessRegistryProperties businessRegistryProperties;
  private RestTemplate restTemplate;

  public UXPLegalEntityRepository(BusinessRegistryProperties businessRegistryProperties,
                                  UXPProperties uxpProperties,
                                  RestTemplate restTemplate) {
    super(uxpProperties);
    this.businessRegistryProperties = businessRegistryProperties;
    this.restTemplate = restTemplate;
  }

  public Collection<String> findRelatedEntityIds(String representativeId) {
    String[] result = restTemplate.exchange(
      getUrl(SERVICE_COMPANIES, 1, format("byPerson/%s", representativeId)),
      GET,
      getHeadersEntity(),
      String[].class
    )
      .getBody();

    return result == null ? emptyList() : asList(result);
  }

  public void restrictBusiness(String personalCode, String reason) {
    // TODO: Not working on business registry side after UXP security server migration
//    HttpEntity<BusinessRestriction> requestEntity = new HttpEntity<>(new BusinessRestriction(personalCode, reason), getHeaders());
//
//    restTemplate.exchange(
//      getUrl(SERVICE_RESTRICTION, 1, null),
//      POST,
//      requestEntity,
//      String.class
//    );
  }

  public LegalEntityDto get(String registryCode) {
    return restTemplate.exchange(
      getUrl(SERVICE_COMPANIES, 1, registryCode),
      GET,
      getHeadersEntity(),
      LegalEntityDto.class
    )
      .getBody();
  }

  public LegalEntityDto[] findAll() {
    return restTemplate.exchange(
      getUrl(SERVICE_COMPANIES, 1, null),
      GET,
      getHeadersEntity(),
      LegalEntityDto[].class
    )
      .getBody();
  }

  @Override
  UXPServiceProviderProperties getServiceProviderProperties() {
    return businessRegistryProperties;
  }

  @AllArgsConstructor
  @Data
  class BusinessRestriction {
    String personIdCode;
    String reason;
  }
}
