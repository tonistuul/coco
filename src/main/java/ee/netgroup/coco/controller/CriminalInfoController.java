package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.CriminalInfo;
import ee.netgroup.coco.service.CriminalInfoService;
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
  private CriminalInfoService criminalInfoService;

  @GetMapping("/person/{personId}")
  public CriminalInfo findByPerson(@PathVariable @NotNull String personId) {
    return CriminalInfo.builder()
      .personId(personId)
      .shouldBeCheckedByCustoms(Math.random() < 0.5)
      .travelRestriction(Math.random() < 0.5)
      .build();
  }
}
