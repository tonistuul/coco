package ee.netgroup.coco.repository;

import ee.netgroup.coco.configuration.BusinessRegistryProperties;
import ee.netgroup.coco.configuration.UXPProperties;
import ee.netgroup.coco.configuration.UXPServiceProviderProperties;
import ee.netgroup.coco.dto.LegalEntityDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

@Repository
public class LegalEntityRepository extends UXPRepository {
  private static final String SERVICE_COMPANIES = "companies";
  private BusinessRegistryProperties businessRegistryProperties;
  private RestTemplate restTemplate;

  public LegalEntityRepository(BusinessRegistryProperties businessRegistryProperties,
                               UXPProperties uxpProperties,
                               RestTemplate restTemplate) {
    super(uxpProperties);
    this.businessRegistryProperties = businessRegistryProperties;
    this.restTemplate = restTemplate;
  }

  public Collection<String> findRelatedEntityIds(String representativeId) {
    String[] result = restTemplate.getForObject(getUrl(SERVICE_COMPANIES, 1, format("byPerson/%s", representativeId)), String[].class);

    return result == null ? emptyList() : asList(result);
  }

  public LegalEntityDto get(String registryCode) {
    return restTemplate.getForObject(getUrl(SERVICE_COMPANIES, 1, registryCode), LegalEntityDto.class);
  }

  @Override
  UXPServiceProviderProperties getServiceProviderProperties() {
    return businessRegistryProperties;
  }
}
