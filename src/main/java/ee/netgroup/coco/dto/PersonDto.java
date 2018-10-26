package ee.netgroup.coco.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class PersonDto {
  private String code;
  private String givenName;
  private Collection<String> middleNames;
  private String familyName;
  private LocalDate birthday;
  private String addressId;
}
