package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LegalEntity {
  private String registryCode;
  private String name;
}
