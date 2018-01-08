package today.smarthealthcare.myhealth.repository;

import today.smarthealthcare.myhealth.entity.BloodPressureMeasurement;
import today.smarthealthcare.myhealth.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurement, Long> {
	List<BloodPressureMeasurement> findByPerson(Person person);
}
