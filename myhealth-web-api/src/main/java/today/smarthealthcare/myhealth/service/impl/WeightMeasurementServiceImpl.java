package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.entity.WeightMeasurement;
import today.smarthealthcare.myhealth.repository.WeightMeasurementRepository;
import today.smarthealthcare.myhealth.service.WeightMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import today.smarthealthcare.myhealth.repository.WeightMeasurementRepository;

@Service
public class WeightMeasurementServiceImpl implements WeightMeasurementService {
	@Autowired
	private WeightMeasurementRepository weightMeasurementRepository;

	@Override
	public WeightMeasurement save(WeightMeasurement weightMeasurement) {
		return weightMeasurementRepository.save(weightMeasurement);
	}

	@Override
	public Iterable<WeightMeasurement> save(Iterable<WeightMeasurement> weightMeasurements) {
		return weightMeasurementRepository.save(weightMeasurements);
	}

	@Override
	public List<WeightMeasurement> findByPerson(Person person) {
		return weightMeasurementRepository.findByPerson(person);
	}
}
