package ee.netgroup.coco.service;

import ee.netgroup.coco.model.CourtCase;
import ee.netgroup.coco.model.LegalEntity;
import ee.netgroup.coco.model.Person;
import ee.netgroup.coco.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CaseService {
  public static final String CLOSED = "CLOSED";
  private final CaseRepository caseRepository;
  private final LegalEntityService legalEntityService;
  private final PersonService personService;

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

  public boolean hasPendingCasesAsDefendant(String personId) {
    Collection<LegalEntity> companies = legalEntityService.findAllWherePersonOnBoard(personId);
    Optional<LegalEntity> any = companies.stream()
      .filter(c -> caseRepository.findByDefendantId(c.getRegistryCode()).stream().anyMatch(cc -> "PENDING".equals(cc.getStatus())))
      .findAny();
    return any.isPresent();
  }

  public Collection<CourtCase> getAllCourtCasesForPerson(String personId) {
    Collection<CourtCase> personCases = caseRepository.findByDefendantIdOrClaimantId(personId,personId);
    Collection<LegalEntity> companies = legalEntityService.findAllWherePersonOnBoard(personId);
    personCases.addAll(companies.stream()
      .map(c -> caseRepository.findByDefendantIdOrClaimantId(c.getRegistryCode(), c.getRegistryCode()))
        .flatMap(Collection::stream)
        .collect(Collectors.toList()));
    return personCases;
  }

  public boolean hasClosedCasesWhereGuilty(String personId) {
    return Math.random() < 0.1;
  }
}
