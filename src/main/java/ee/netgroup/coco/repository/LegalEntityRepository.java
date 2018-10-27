package ee.netgroup.coco.repository;

import ee.netgroup.coco.dto.LegalEntityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static java.util.Arrays.asList;

@Repository
public class LegalEntityRepository {
  private RestTemplate restTemplate;
  private String legalEntityRegistryUrl;

  public LegalEntityRepository(RestTemplate restTemplate,
                               @Value("${legalEntityRegistry.url}") String legalEntityRegistryUrl) {
    this.restTemplate = restTemplate;
    this.legalEntityRegistryUrl = legalEntityRegistryUrl;
  }

  public Collection<String> findRelatedEntityIds(String representativeId) {
    String[] result = restTemplate.getForObject(legalEntityRegistryUrl + "/companies/byPerson/{representativeId}", String[].class, representativeId);
    return asList(result);
  }

  public LegalEntityDto get(String registryCode) {
    return restTemplate.getForObject(legalEntityRegistryUrl + "/companies/{registryCode}", LegalEntityDto.class, registryCode);
  }
}
