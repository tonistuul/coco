package ee.netgroup.coco.repository;

import ee.netgroup.coco.model.CourtCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CaseRepository extends JpaRepository<CourtCase, Long> {
  Collection<CourtCase> findByDefendantId(String registryCode);
}
