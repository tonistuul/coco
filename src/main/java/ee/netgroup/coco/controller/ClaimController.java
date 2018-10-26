package ee.netgroup.coco.controller;

import ee.netgroup.coco.domain.Claim;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Collection;

import static java.util.Arrays.asList;

@RestController
@AllArgsConstructor
@RequestMapping("claims")
public class ClaimController {

  @GetMapping("/{claimId}")
  public Claim getClaim(@PathVariable @NotNull String claimId) {
    return Claim.builder()
      .id(claimId)
      .caseType("LAMBI KEISS")
      .description("Keegi tegi midagi halba")
      .value("123432")
      .fee("100")
      .build();
  }

  @GetMapping("/byPerson/{personId}")
  public Collection<Claim> findAllByPerson(@PathVariable @NotNull String personId) {
    return asList(
      Claim.builder()
        .id("10")
        .caseType("LAMBI KEISS")
        .description("Keegi tegi midagi halba")
        .value("123432")
        .fee("100")
        .build(),
      Claim.builder()
        .id("20")
        .caseType("HUVITAV KEISS")
        .description("Keegi tegi midagi head, aga sai ikka karistada")
        .value("125")
        .fee("10")
        .build()
    );
  }
}
