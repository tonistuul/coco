package ee.netgroup.coco.service;

import ee.netgroup.coco.domain.LegalEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.Arrays.asList;

@Service
public class LegalEntityService {

  public Collection<LegalEntity> findWherePersonOnBoard(String personId) {
    return asList(
      LegalEntity.builder().entityId("1337").name("Poobus Holdings OY").build(),
      LegalEntity.builder().entityId("010101010").name("Lelle Kuningriik LLC").build()
    );
  }
}
