package ee.netgroup.coco.repository;

import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.dto.PersonDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Repository
public class PersonRepository {
  private RestTemplate restTemplate;
  private String personRegistryUrl;

  public PersonRepository(RestTemplate restTemplate,
                          @Value("${personRegistry.url}") String legalEntityRegistryUrl) {
    this.restTemplate = restTemplate;
    this.personRegistryUrl = legalEntityRegistryUrl;
  }

  public Person get(String personId) {
    PersonDto dto = restTemplate.getForObject(personRegistryUrl + "/persons/{personId}", PersonDto.class, personId);
    if (dto == null) {
      throw new RuntimeException(format("Failed to find person id: %s in person registry", personId));
    }

    return Person.builder()
      .firstName(dto.getGivenName())
      .lastName(dto.getFamilyName())
      .dateOfBirth(dto.getBirthday())
      .address(dto.getAddressId())
      .personId(dto.getCode())
      .build();
  }
}
