package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.HeightMeasurement;
import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.repository.HeightMeasurementRepository;
import today.smarthealthcare.myhealth.service.HeightMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import today.smarthealthcare.myhealth.repository.HeightMeasurementRepository;

@Service
public class HeightMeasurementServiceImpl implements HeightMeasurementService{
	@Autowired
	private HeightMeasurementRepository heightMeasurementRepository;

	@Override
	public HeightMeasurement save(HeightMeasurement heightMeasurement) {
		return heightMeasurementRepository.save(heightMeasurement);
	}

	@Override
	public List<HeightMeasurement> findByPerson(Person person) {
		return heightMeasurementRepository.findByPerson(person);
	}
}
