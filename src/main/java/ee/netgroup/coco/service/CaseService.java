package ee.netgroup.coco.service;

import ee.netgroup.coco.model.CourtCase;
import ee.netgroup.coco.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CaseService {
  public static final String CLOSED = "CLOSED";
  private final CaseRepository caseRepository;
  private final LegalEntityService legalEntityService;

  public CourtCase saveCase(CourtCase courtCase) {
    if (courtCase.getDateOfRegistration() != null) {
      courtCase.setDateOfRegistration(LocalDate.now());
    }
    return caseRepository.save(courtCase);
  }

  public CourtCase updateCase(CourtCase courtCase) {
    if (CLOSED.equals(courtCase.getStatus()) && courtCase.getBusinessRestrictedRegistryCode() != null) {
      legalEntityService.restrictBusiness(courtCase.getBusinessRestrictedRegistryCode(), format("Case: %s", courtCase.getCaseNumber()));
    }
    return caseRepository.save(courtCase);
  }

  public List<CourtCase> findAll() {
    return caseRepository.findAll();
  }
}
