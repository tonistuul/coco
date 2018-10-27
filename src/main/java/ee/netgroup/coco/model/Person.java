package ee.netgroup.coco.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Builder
public class Person {
  private String personId;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String address;
  private Collection<LegalEntity> legalEntities;
}
