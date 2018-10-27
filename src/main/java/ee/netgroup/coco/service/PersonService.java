package ee.netgroup.coco.service;

import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
  private LegalEntityService legalEntityService;
  private PersonRepository personRepository;

  public Person getPerson(String personId) {
    Person result = personRepository.get(personId);
    result.setLegalEntities(legalEntityService.findAllWherePersonOnBoard(personId));

    return result;
  }
}
