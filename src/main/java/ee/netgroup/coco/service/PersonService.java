package ee.netgroup.coco.service;

import ee.netgroup.coco.domain.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
  private LegalEntityService legalEntityService;

  public Person getPerson(String personId) {
    return Person.builder().personId(personId).legalEntities(legalEntityService.findWherePersonOnBoard(personId)).build();
  }
}
