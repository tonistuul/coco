package ee.netgroup.coco.model;

import ee.netgroup.coco.dto.LegalEntityDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

  public static List<LegalEntity> toLegalEntities(LegalEntityDto[] dtos) {
    List<LegalEntity> result = new ArrayList<LegalEntity>();
    for (LegalEntityDto dto : dtos) {
      result.add(toLegalEntity(dto));
    }
    return result;
  }
}
