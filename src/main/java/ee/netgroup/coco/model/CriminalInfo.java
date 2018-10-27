package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriminalInfo {
  private String personId;
  private boolean travelRestriction;
  private boolean shouldBeCheckedByCustoms;
}
