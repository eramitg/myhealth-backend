package today.smarthealthcare.myhealth.repository;


import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.entity.WeightMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightMeasurementRepository extends JpaRepository<WeightMeasurement, Long> {
	List<WeightMeasurement> findByPerson(Person person);
}
