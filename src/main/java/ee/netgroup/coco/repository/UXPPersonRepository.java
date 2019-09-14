package ee.netgroup.coco.repository;

import ee.netgroup.coco.configuration.PersonRegistryProperties;
import ee.netgroup.coco.configuration.UXPProperties;
import ee.netgroup.coco.configuration.UXPServiceProviderProperties;
import ee.netgroup.coco.dto.PersonDto;
import ee.netgroup.coco.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.GET;

@Repository
public class UXPPersonRepository extends UXPRepository {
  private static final String SERVICE_PERSONS = "persons";
  private static final String FIND_ALL_PARAMS = "&&dateFrom=" + LocalDateTime.of(1900, 1, 1, 1, 1).toString();
  private PersonRegistryProperties personRegistryProperties;
  private RestTemplate restTemplate;

  public UXPPersonRepository(PersonRegistryProperties personRegistryProperties, UXPProperties uxpProperties, RestTemplate restTemplate) {
    super(uxpProperties);
    this.personRegistryProperties = personRegistryProperties;
    this.restTemplate = restTemplate;
  }

  public Person get(String personId) {
    PersonDto dto = restTemplate.exchange(
      getUrl(SERVICE_PERSONS, 1, personId),
      GET,
      getHeadersEntity(),
      PersonDto.class
    )
      .getBody();

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
    PersonDto[] result = restTemplate.exchange(
      getUrl(SERVICE_PERSONS, 1, null) + FIND_ALL_PARAMS,
      GET,
      getHeadersEntity(),
      PersonDto[].class
    )
      .getBody();

    return result == null ? emptyList() : Arrays.stream(result)
      .map(dto -> Person.builder()
        .firstName(dto.getGivenName())
        .lastName(dto.getFamilyName())
        .dateOfBirth(dto.getBirthday())
        .address(dto.getAddressId())
        .personId(dto.getCode())
        .build())
      .collect(toList());
  }
}
