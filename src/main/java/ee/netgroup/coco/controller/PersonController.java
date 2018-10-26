package ee.netgroup.coco.controller;

import ee.netgroup.coco.domain.Person;
import ee.netgroup.coco.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping("person")
public class PersonController {
  private PersonService personService;

  @GetMapping("/{personId}")
  public Person getPerson(@PathVariable @NotNull String personId) {
    return personService.getPerson(personId);
  }
}
