package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Claim {
  private String id;
  private String caseType;
  private String description;
  private String value;
  private String fee;
}
