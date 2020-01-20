package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
public class Hearing {
  @Id
  @GeneratedValue
  private Long id;
  private String caseNumber;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String judge;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "hearing_id", nullable = false)
  private List<HearingParticipant> participants;
}
