package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegalEntity {
  private String registryCode;
  private String name;
}
