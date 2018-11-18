package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.CriminalInfo;
import ee.netgroup.coco.service.CaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping("criminal-info")
public class CriminalInfoController {
  private CaseService caseService;

  @GetMapping("/person/{personId}")
  public CriminalInfo findByPerson(@PathVariable @NotNull String personId) {
    return CriminalInfo.builder()
      .personId(personId)
      .shouldBeCheckedByCustoms(caseService.hasClosedCasesWhereGuilty(personId))
      .travelRestriction(caseService.hasPendingCasesAsDefendant(personId))
      .build();
  }
}
