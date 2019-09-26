package ee.netgroup.coco.service;

import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.repository.PersonRepository;
import ee.netgroup.coco.repository.UXPPersonRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService {
  private LegalEntityService legalEntityService;
  private UXPPersonRepository uxpPersonRepository;
  private PersonRepository personRepository;

  public Person getPerson(String personId) {
    Person result = uxpPersonRepository.get(personId);
    result.setLegalEntities(legalEntityService.findAllWherePersonOnBoard(personId));

    return result;
  }

  public List<Person> findAll() {
    return uxpPersonRepository.findAll();
  }

  Person save(Person person) {
    return personRepository.save(person);
  }

  public List<Person> findAllByCompanyCode(String companyCode) {
    return legalEntityService.findAllBoardMembers(companyCode).parallelStream()
      .filter(bm -> StringUtils.isNotEmpty(bm.getPersonIdCode()))
      .map(bm -> uxpPersonRepository.get(bm.getPersonIdCode()))
      .collect(Collectors.toList());

  }
}
