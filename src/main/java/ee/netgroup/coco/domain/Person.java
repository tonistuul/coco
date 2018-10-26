package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class Person {
  private String personId;
  private Collection<LegalEntity> legalEntities;
}
