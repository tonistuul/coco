package ee.netgroup.coco.controller;

import ee.netgroup.coco.domain.Claim;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping("claim")
public class ClaimController {

  @GetMapping("/{claimId}")
  public Claim getPerson(@PathVariable @NotNull String claimId) {
    return Claim.builder()
      .id(claimId)
      .caseType("LAMBI KEISS")
      .description("Keegi tegi midagi halba")
      .value("123432")
      .fee("100")
      .build();
  }
}
