package ee.netgroup.coco.service;

import ee.netgroup.coco.dto.LegalEntityMemberDto;
import ee.netgroup.coco.model.LegalEntity;
import ee.netgroup.coco.repository.UXPLegalEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LegalEntityService {
  private UXPLegalEntityRepository UXPLegalEntityRepository;

  public Collection<LegalEntity> findAllWherePersonOnBoard(String personId) {
    Collection<String> relations = UXPLegalEntityRepository.findRelatedEntityIds(personId);

    return relations.stream()
      .map(relation -> UXPLegalEntityRepository.get(relation))
      .filter(le -> le.isOnBoard(personId))
      .map(LegalEntity::toLegalEntity)
      .collect(toList());
  }

  public Collection<LegalEntityMemberDto> findAllBoardMembers(String registryCode) {
    return UXPLegalEntityRepository.get(registryCode).getMembers().stream()
      .filter(LegalEntityMemberDto::isBoardMember)
      .collect(Collectors.toSet());
  }

  public LegalEntity get(String entityId) {
    return LegalEntity.toLegalEntity(UXPLegalEntityRepository.get(entityId));
  }

  public void restrictBusiness(String businessRestrictedRegistryCode, String reason) {
    findAllBoardMembers(businessRestrictedRegistryCode).forEach(p -> UXPLegalEntityRepository.restrictBusiness(p.getPersonIdCode(), reason));
  }
}
