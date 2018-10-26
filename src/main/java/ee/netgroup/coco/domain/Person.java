package ee.netgroup.coco.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Builder
public class Person {
  private String personId;
  private String firstName;
  private String middleName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String address;
  private String email;
  private Collection<LegalEntity> legalEntities;
}
