package ee.netgroup.coco.service;

import ee.netgroup.coco.model.CourtCase;
import ee.netgroup.coco.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseService {
  private final CaseRepository caseRepository;

  public CourtCase saveCase(CourtCase courtCase) {
    if (courtCase.getDateOfRegistration() != null) {
      courtCase.setDateOfRegistration(LocalDate.now());
    }
    return caseRepository.save(courtCase);
  }

  public List<CourtCase> findAll() {
    return caseRepository.findAll();
  }
}
