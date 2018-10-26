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

  public boolean authenticate(String username, String password) {
    SystemUser user = systemUserRepository.findFirstByUsernameAndPassword(username, password);
    return user != null;
  };
}
