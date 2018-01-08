package today.smarthealthcare.myhealth.repository;

import today.smarthealthcare.myhealth.entity.HeightMeasurement;
import today.smarthealthcare.myhealth.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeightMeasurementRepository extends JpaRepository<HeightMeasurement, Long> {
	List<HeightMeasurement> findByPerson(Person person);
}
