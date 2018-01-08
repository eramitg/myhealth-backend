package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.BloodPressureMeasurement;
import today.smarthealthcare.myhealth.entity.Person;

import java.util.Iterator;
import java.util.List;

public interface BloodPressureMeasurementService {
	BloodPressureMeasurement save(BloodPressureMeasurement bloodPressureMeasurement);
	Iterable<BloodPressureMeasurement> save(Iterable<BloodPressureMeasurement> bloodPressureMeasurements);
	List<BloodPressureMeasurement> findByPerson(Person person);

}
