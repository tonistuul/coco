package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegalEntity {
  private String entityId;
  private String name;
}
