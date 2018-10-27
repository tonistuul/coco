package ee.netgroup.coco.model;

import ee.netgroup.coco.dto.LegalEntityDto;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class LegalEntity {
  private String registryCode;
  private String name;
  private Collection<String> activities;

  public static LegalEntity toLegalEntity(LegalEntityDto dto) {
    return LegalEntity.builder()
      .name(dto.getName())
      .registryCode(dto.getRegNumber())
      .activities(dto.getActivities())
      .build();
  }
}
