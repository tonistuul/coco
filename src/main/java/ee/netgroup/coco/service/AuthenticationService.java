package ee.netgroup.coco.service;

import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final PersonRepository personRepository;

  @Autowired
  public AuthenticationService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person authenticate(String personalCode) {
    return personRepository.get(personalCode);
  }
}
