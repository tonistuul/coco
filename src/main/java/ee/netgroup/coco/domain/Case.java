package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Case {
  private String status;
  private String paymentStatus = "PAID";
  private String caseNumber;
  private String type;
  private LocalDate dateOfRegistration;
  private String judge;
  private String claimantId;
  private String defendantId;
  private List<String> files;

  private String caseType;
  private String description;
  private String value;
  private String fee;}
