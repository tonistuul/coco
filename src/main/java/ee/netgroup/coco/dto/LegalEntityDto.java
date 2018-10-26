package ee.netgroup.coco.dto;

import lombok.Data;

import java.util.List;

@Data
public class LegalEntityDto {
  private String companyId;
  private String name;
  private List<LegalEntityMemberDto> members;
  private List<String> activityNames;

  public boolean isOnBoard(String personId) {
    return members.stream().anyMatch(m -> personId.equals(m.getPersonIdCode()) && m.isBoardMember());
  }
}
