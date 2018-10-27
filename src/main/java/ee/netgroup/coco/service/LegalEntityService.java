package ee.netgroup.coco.service;

import ee.netgroup.coco.model.LegalEntity;
import ee.netgroup.coco.repository.LegalEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LegalEntityService {
  private LegalEntityRepository legalEntityRepository;

  public Collection<LegalEntity> findAllWherePersonOnBoard(String personId) {
    Collection<String> relations = legalEntityRepository.findRelatedEntityIds(personId);

    return relations.stream()
      .map(relation -> legalEntityRepository.get(relation))
      .filter(le -> le.isOnBoard(personId))
      .map(dto -> LegalEntity.builder()
        .name(dto.getName())
        .registryCode(dto.getCompanyId())
        .build())
      .collect(toList());
  }
}
