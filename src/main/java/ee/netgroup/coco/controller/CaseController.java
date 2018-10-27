package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.CourtCase;
import ee.netgroup.coco.service.CaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

import static java.util.Arrays.asList;

@RestController
@AllArgsConstructor
@RequestMapping("cases")
public class CaseController {
  private CaseService caseService;

  @GetMapping("/byPerson/{personId}")
  public Collection<CourtCase> findAllByPerson(@PathVariable @NotNull String personId) {
    return asList(
      CourtCase.builder()
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

  @GetMapping
  public Collection<CourtCase> findAll() {
    return caseService.findall();
  }

  @PostMapping
  public CourtCase saveCase(@RequestBody CourtCase courtCase) {
    return caseService.saveCase(courtCase);
  }
}
