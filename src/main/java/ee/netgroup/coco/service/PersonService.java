package ee.netgroup.coco.service;

import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.repository.PersonRepository;
import ee.netgroup.coco.repository.UXPPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
  private LegalEntityService legalEntityService;
  private UXPPersonRepository UXPPersonRepository;
  private PersonRepository personRepository;

  public Person getPerson(String personId) {
    Person result = UXPPersonRepository.get(personId);
    result.setLegalEntities(legalEntityService.findAllWherePersonOnBoard(personId));

    return result;
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  Person save(Person person) {
    return personRepository.save(person);
  }
}
