package ee.netgroup.coco.repository;

import ee.netgroup.coco.model.CourtCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public interface CaseRepository extends JpaRepository<CourtCase, Long> {
  Collection<CourtCase> findByDefendantId(String registryCode);

  Collection<CourtCase> findByDefendantIdOrClaimantId(String defendantId, String claimantId);
}