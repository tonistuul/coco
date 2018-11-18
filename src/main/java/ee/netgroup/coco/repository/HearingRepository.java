package ee.netgroup.coco.repository;

import ee.netgroup.coco.model.Hearing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HearingRepository extends JpaRepository<Hearing, Long> {
  List<Hearing> findHearingsByParticipantsPersonId(String person);
}
