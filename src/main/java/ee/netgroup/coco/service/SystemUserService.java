package ee.netgroup.coco.service;

import ee.netgroup.coco.model.SystemUser;
import ee.netgroup.coco.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService {
  private final SystemUserRepository systemUserRepository;

  @Autowired
  public SystemUserService(SystemUserRepository systemUserRepository) {
    this.systemUserRepository = systemUserRepository;
  }

  public SystemUser authenticate(String personalCode, String password) {
    return systemUserRepository.findFirstByPersonalCodeAndPassword(personalCode, password);
  };
}
