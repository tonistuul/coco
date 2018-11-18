package ee.netgroup.coco.service;

import ee.netgroup.coco.model.Hearing;
import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.repository.HearingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HearingService {
  private final HearingRepository repository;

  @Autowired
  public HearingService(HearingRepository repository) {
    this.repository = repository;
  }

  public Hearing saveHearing(Hearing hearing) {
    return repository.save(hearing);
  }

  public List<Hearing> getHearings(String personalCode) {
    return repository.findHearingsByParticipantsPersonId(personalCode);
  }
}
