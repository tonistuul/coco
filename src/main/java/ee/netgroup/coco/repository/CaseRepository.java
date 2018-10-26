package ee.netgroup.coco.repository;

import ee.netgroup.coco.domain.CourtCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<CourtCase, Long> {
}
