package ee.netgroup.coco.controller;

import ee.netgroup.coco.domain.Case;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

import static java.util.Arrays.asList;

@RestController
@AllArgsConstructor
@RequestMapping("cases")
public class CaseController {

  @GetMapping("/byPerson/{personId}")
  public Collection<Case> findAllByPerson(@PathVariable @NotNull String personId) {
    return asList(
      Case.builder()
        .caseNumber("112-CIVI-2018")
        .claimantId(personId)
        .dateOfRegistration(LocalDate.now())
        .defendantId("asdads")
        .judge("Judy")
        .status("PENDING")
        .type("Contract dispute")
        .build()
    );
  }
}
