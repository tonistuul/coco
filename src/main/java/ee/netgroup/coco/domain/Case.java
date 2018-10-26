package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Case {
  private String status;
  private String caseNumber;
  private String type;
  private LocalDate dateOfRegistration;
  private String judge;
  private String claimantId;
  private String defendantId;
}
