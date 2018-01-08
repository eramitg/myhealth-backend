package today.smarthealthcare.myhealth.repository;

import today.smarthealthcare.myhealth.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	Person findById(long id);
}
