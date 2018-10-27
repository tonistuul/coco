package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class CourtCase {

  @Id
  @GeneratedValue
  private Long id;

  private String status;
  private String paymentStatus = "PAID";
  private String caseNumber;
  private String type;
  private LocalDate dateOfRegistration;
  private String judge;
  private String claimantId;
  private String defendantId;
  private String caseType;
  private String description;
  private String value;
  private String fee;
}