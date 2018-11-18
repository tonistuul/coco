package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
  @ManyToMany
  private List<Person> participants;
}
