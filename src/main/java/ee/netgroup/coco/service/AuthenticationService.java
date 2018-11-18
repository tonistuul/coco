package ee.netgroup.coco.service;

import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.repository.UXPPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final UXPPersonRepository UXPPersonRepository;
  private final PersonService personService;

  @Autowired
  public AuthenticationService(UXPPersonRepository UXPPersonRepository,
                               PersonService personService) {
    this.UXPPersonRepository = UXPPersonRepository;
    this.personService = personService;
  }

  public Person authenticate(String personalCode) {
    Person person = UXPPersonRepository.get(personalCode);

    if( person != null) {
      person = personService.save(person);
    }

    return person;
  }
}
