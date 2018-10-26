package ee.netgroup.coco.repository;

import ee.netgroup.coco.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
  SystemUser findFirstByUsernameAndPassword(String username, String password);
}
