package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Hearing {
  private String caseNumber;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String judge;
}
