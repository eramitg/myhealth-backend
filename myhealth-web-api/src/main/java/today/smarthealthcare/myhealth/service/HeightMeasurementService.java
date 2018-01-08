package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.HeightMeasurement;
import today.smarthealthcare.myhealth.entity.Person;

import java.util.List;

public interface HeightMeasurementService {
	HeightMeasurement save(HeightMeasurement weightMeasurement);
	List<HeightMeasurement> findByPerson(Person person);
}
