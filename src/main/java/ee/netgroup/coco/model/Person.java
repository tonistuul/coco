package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@Builder
public class Person {
  @Id
  private String personId;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String address;
  @Transient
  private Collection<LegalEntity> legalEntities;
}
