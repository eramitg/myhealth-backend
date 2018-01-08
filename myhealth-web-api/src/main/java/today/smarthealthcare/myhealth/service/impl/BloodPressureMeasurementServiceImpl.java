package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.BloodPressureMeasurement;
import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.repository.BloodPressureMeasurementRepository;
import today.smarthealthcare.myhealth.service.BloodPressureMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import today.smarthealthcare.myhealth.repository.BloodPressureMeasurementRepository;

@Service
public class BloodPressureMeasurementServiceImpl implements BloodPressureMeasurementService {
	@Autowired
	private BloodPressureMeasurementRepository bloodPressureMeasurementRepository;

	@Override
	public BloodPressureMeasurement save(BloodPressureMeasurement bloodPressureMeasurement) {
		return bloodPressureMeasurementRepository.save(bloodPressureMeasurement);
	}

	@Override
	public Iterable<BloodPressureMeasurement> save(Iterable<BloodPressureMeasurement> bloodPressureMeasurements) {
		return bloodPressureMeasurementRepository.save(bloodPressureMeasurements);
	}

	@Override
	public List<BloodPressureMeasurement> findByPerson(Person person) {
		return bloodPressureMeasurementRepository.findByPerson(person);
	}
}
