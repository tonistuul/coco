package ee.netgroup.coco.repository;

import ee.netgroup.coco.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
