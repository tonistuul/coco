package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.Hearing;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

import static java.util.Arrays.asList;

@RestController
@AllArgsConstructor
@RequestMapping("hearings")
public class HearingController {

  @GetMapping("/byPerson/{personId}")
  public Collection<Hearing> findAllByPerson(@PathVariable @NotNull String personId) {
    return asList(
      Hearing.builder()
        .caseNumber("134-CIVI-2018")
        .startTime(LocalDateTime.of(2018, 10, 28, 18, 30))
        .endTime(LocalDateTime.of(2018, 10, 28, 19, 0))
        .judge("Timukas Boris")
        .build(),
      Hearing.builder()
        .caseNumber("134-CIVI-2018")
        .startTime(LocalDateTime.of(2018, 11, 1, 10, 30))
        .endTime(LocalDateTime.of(2018, 11, 1, 13, 0))
        .judge("Judy")
        .build()
    );
  }
}
