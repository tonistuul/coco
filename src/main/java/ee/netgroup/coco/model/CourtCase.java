package ee.netgroup.coco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
  private LocalDate dateOfRegistration;
  private String judge;
  private String claimantId;
  private String defendantId;
  private String caseType;
  private String description;
  private String value;
  private String fee;

  private String judgmentFormType;
  private String judgmentDescription;
  private Integer sanction;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "case_id", nullable = false)
  private List<Document> documents;

  @Transient
  private LegalEntity claimant;
  @Transient
  private LegalEntity defendant;

  @JsonIgnore
  public String getBusinessRestrictedRegistryCode() {
    if (sanction == 1) {
      return claimantId;
    }
    else if (sanction == 2) {
      return defendantId;
    }
    return null;
  }
}
