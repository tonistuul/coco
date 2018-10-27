package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.CourtCase;
import ee.netgroup.coco.service.CaseService;
import ee.netgroup.coco.service.LegalEntityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
@RequestMapping("cases")
public class CaseController {
  private CaseService caseService;
  private LegalEntityService legalEntityService;


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
        .caseType("Contract dispute")
        .build()
    );
  }

  @GetMapping
  public Collection<CourtCase> findAll() {
    List<CourtCase> findall = caseService.findall();
    try {
      return findall.stream().map(c -> {
        if (c.getClaimantId() != null && c.getDefendantId() != null) {
          c.setClaimant(legalEntityService.get(c.getClaimantId()));
          c.setDefendant(legalEntityService.get(c.getDefendantId()));
        }
        return c;
      })
        .collect(toList());
    }
    catch (Exception e) {
      return findall;
    }
  }

  @PostMapping
  public CourtCase saveCase(@RequestBody CourtCase courtCase) {
    return caseService.saveCase(courtCase);
  }
}
