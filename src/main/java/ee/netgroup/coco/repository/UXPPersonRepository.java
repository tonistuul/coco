package ee.netgroup.coco.repository;

import ee.netgroup.coco.configuration.PersonRegistryProperties;
import ee.netgroup.coco.configuration.UXPProperties;
import ee.netgroup.coco.configuration.UXPServiceProviderProperties;
import ee.netgroup.coco.dto.PersonDto;
import ee.netgroup.coco.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.lang.String.format;

@Repository
public class UXPPersonRepository extends UXPRepository {
  private static final String SERVICE_PERSONS = "persons";
  private PersonRegistryProperties personRegistryProperties;
  private RestTemplate restTemplate;

  public UXPPersonRepository(PersonRegistryProperties personRegistryProperties, UXPProperties uxpProperties, RestTemplate restTemplate) {
    super(uxpProperties);
    this.personRegistryProperties = personRegistryProperties;
    this.restTemplate = restTemplate;
  }

  public Person get(String personId) {
    PersonDto dto = restTemplate.getForObject(getUrl(SERVICE_PERSONS, 1, personId), PersonDto.class);

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

  @Override
  UXPServiceProviderProperties getServiceProviderProperties() {
    return personRegistryProperties;
  }

  public List<Person> findAll() {
    PersonDto dto = restTemplate.getForObject(getUrl(SERVICE_PERSONS, 1, null), PersonDto.class);

//    return Person.builder()
//      .firstName(dto.getGivenName())
//      .lastName(dto.getFamilyName())
//      .dateOfBirth(dto.getBirthday())
//      .address(dto.getAddressId())
//      .personId(dto.getCode())
//      .build();
    return null;
  }
}
