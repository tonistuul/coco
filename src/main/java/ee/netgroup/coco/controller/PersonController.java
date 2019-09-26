package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("persons")
public class PersonController {
  private PersonService personService;

  @GetMapping("/{personId}")
  public Person getPerson(@PathVariable @NotNull String personId) {
    return personService.getPerson(personId);
  }

  @GetMapping
  public List<Person> findAll() {
    return personService.findAll();
  }

  @GetMapping("/byCompanyCode/{companyCode}")
  public List<Person> findBoardMembersByCompanyCode(@PathVariable @NotNull String companyCode) {
    return personService.findAllByCompanyCode(companyCode);
  }
}
