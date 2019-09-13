package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.LegalEntity;
import ee.netgroup.coco.service.LegalEntityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("legal-entities")
public class LegalEntityController {
  private LegalEntityService legalEntityService;

  @GetMapping("/{entityId}")
  public LegalEntity getLegalEntity(@PathVariable @NotNull String entityId) {
    return legalEntityService.get(entityId);
  }

  @GetMapping()
  public List<LegalEntity> findAll() {
    return legalEntityService.findAll();
  }
}
