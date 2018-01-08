package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.entity.WeightMeasurement;

import java.util.List;

import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.entity.WeightMeasurement;

public interface WeightMeasurementService {
	WeightMeasurement save(WeightMeasurement weightMeasurement);
	Iterable<WeightMeasurement> save(Iterable<WeightMeasurement> weightMeasurements);
	List<WeightMeasurement> findByPerson(Person person);
}
