package ee.netgroup.coco.dto;

import lombok.Data;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Data
public class LegalEntityDto {
  private String regNumber;
  private String name;
  private List<LegalEntityMemberDto> members;
  private List<ActivityDto> activities;

  public boolean isOnBoard(String personId) {
    return members.stream().anyMatch(m -> personId.equals(m.getPersonIdCode()) && m.isBoardMember());
  }

  public Collection<String> getActivities() {
    return activities == null ? emptyList() : activities.stream().map(ActivityDto::getDescription).collect(toList());
  }
}
