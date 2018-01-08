package today.smarthealthcare.myhealth.controller;

import today.smarthealthcare.myhealth.dto.BloodPressureMeasurementDto;
import today.smarthealthcare.myhealth.dto.HeightMeasurementDto;
import today.smarthealthcare.myhealth.dto.WeightMeasurementDto;
import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.service.BloodPressureMeasurementService;
import today.smarthealthcare.myhealth.service.HeightMeasurementService;
import today.smarthealthcare.myhealth.service.MyHealthUserService;
import today.smarthealthcare.myhealth.service.WeightMeasurementService;
import today.smarthealthcare.myhealth.service.mapper.BloodPressureMeasurementMapper;
import today.smarthealthcare.myhealth.service.mapper.HeightMeasurementMapper;
import today.smarthealthcare.myhealth.service.mapper.WeightMeasurementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeasurementController {
	@Autowired
	private MyHealthUserService myHealthUserService;
	@Autowired
	private WeightMeasurementService weightMeasurementService;
	@Autowired
	private WeightMeasurementMapper weightMeasurementMapper;
	@Autowired
	private HeightMeasurementService heightMeasurementService;
	@Autowired
	private HeightMeasurementMapper heightMeasurementMapper;
	@Autowired
	private BloodPressureMeasurementService bloodPressureMeasurementService;
	@Autowired
	private BloodPressureMeasurementMapper bloodPressureMeasurementMapper;


	@RequestMapping(value = "/rest/measurement/weight", method = RequestMethod.POST)
	@ResponseBody
	public WeightMeasurementDto saveWeightMeasurement(@RequestBody WeightMeasurementDto weightMeasurementDto) {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		//TODO Now we consider that user have only 1 person assigned. In future should be changed to  handle many persons
		weightMeasurementDto.setPersonId(myHealthUser.getPersons().get(0).getId());

		return weightMeasurementMapper.toWeightMeasurementDto(
				weightMeasurementService.save(weightMeasurementMapper.toWeightMeasurement(weightMeasurementDto)));
	}

	@RequestMapping("/rest/measurement/weight")
	@ResponseBody
	public List<WeightMeasurementDto> findAllWeightMeasurementsByPerson() {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		//TODO Now we consider that user have only 1 person assigned. In future should be changed to  handle many persons
		return weightMeasurementMapper.toWeightMeasurementDtos(weightMeasurementService.findByPerson(myHealthUser.getPersons().get(0)));

	}

	@RequestMapping(value = "/rest/measurement/height", method = RequestMethod.POST)
	@ResponseBody
	public HeightMeasurementDto saveHeightMeasurement(@RequestBody HeightMeasurementDto heightMeasurementDto) {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		//TODO Now we consider that user have only 1 person assigned. In future should be changed to  handle many persons
		heightMeasurementDto.setPersonId(myHealthUser.getPersons().get(0).getId());

		return heightMeasurementMapper.toHeightMeasurementDto(
				heightMeasurementService.save(heightMeasurementMapper.toHeightMeasurement(heightMeasurementDto)));
	}

	@RequestMapping("/rest/measurement/height")
	@ResponseBody
	public List<HeightMeasurementDto> findAllHeightMeasurementsByPerson() {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		//TODO Now we consider that user have only 1 person assigned. In future should be changed to  handle many persons
		return heightMeasurementMapper.toHeightHeasurementDtos(heightMeasurementService.findByPerson(myHealthUser.getPersons().get(0)));

	}

	@RequestMapping(value = "/rest/measurement/bp", method = RequestMethod.POST)
	@ResponseBody
	public BloodPressureMeasurementDto saveBpMeasurement(@RequestBody BloodPressureMeasurementDto bloodPressureMeasurementDto) {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		//TODO Now we consider that user have only 1 person assigned. In future should be changed to  handle many persons
		bloodPressureMeasurementDto.setPersonId(myHealthUser.getPersons().get(0).getId());

		return bloodPressureMeasurementMapper.toBloodPressureMeasurementDto(
				bloodPressureMeasurementService.save(bloodPressureMeasurementMapper.toBloodPressureMeasurement(bloodPressureMeasurementDto)));
	}

	@RequestMapping("/rest/measurement/bp")
	@ResponseBody
	public List<BloodPressureMeasurementDto> findAllBpMeasurementsByPerson() {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		//TODO Now we consider that user have only 1 person assigned. In future should be changed to  handle many persons
		return bloodPressureMeasurementMapper.toBloodPressureMeasurementDtos(bloodPressureMeasurementService.findByPerson(myHealthUser.getPersons().get(0)));

	}


}
