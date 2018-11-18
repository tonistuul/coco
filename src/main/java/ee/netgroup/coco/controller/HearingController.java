package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.Hearing;
import ee.netgroup.coco.service.HearingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("hearings")
public class HearingController {

  private final HearingService hearingService;

  @GetMapping("/byPerson/{personId}")
  public Collection<Hearing> findAllByPerson(@PathVariable @NotNull String personId) {
    return hearingService.getHearings(personId);
  }

  @PostMapping("/add")
  public ResponseEntity addHearing(@RequestBody Hearing hearing) {
    Hearing savedHearing = hearingService.saveHearing(hearing);
    if (savedHearing != null) {
      return ResponseEntity.created(null).body(savedHearing);
    }

    return ResponseEntity.badRequest().build();
  }
}
