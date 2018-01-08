package today.smarthealthcare.myhealth.service.mapper;

import today.smarthealthcare.myhealth.dto.BloodPressureMeasurementDto;
import today.smarthealthcare.myhealth.entity.BloodPressureMeasurement;
import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthBpDataDto;
import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthWeightDataDto;
import today.smarthealthcare.myhealth.ihealth.util.IHealthUtils;
import today.smarthealthcare.myhealth.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BloodPressureMeasurementMapper {
	@Autowired
	private PersonService personService;

	public BloodPressureMeasurement toBloodPressureMeasurement(BloodPressureMeasurementDto bloodPressureMeasurementDto) {
		if (bloodPressureMeasurementDto != null) {
			BloodPressureMeasurement bloodPressureMeasurement = new BloodPressureMeasurement();
			bloodPressureMeasurement.setId(bloodPressureMeasurementDto.getId());
			bloodPressureMeasurement.setComments(bloodPressureMeasurementDto.getComments());
			bloodPressureMeasurement.setDateTaken(bloodPressureMeasurementDto.getDate());
			bloodPressureMeasurement.setDiastolic(bloodPressureMeasurementDto.getDiastolic());
			bloodPressureMeasurement.setSystolic(bloodPressureMeasurementDto.getSystolic());
			bloodPressureMeasurement.setPerson(personService.findById(bloodPressureMeasurementDto.getPersonId()));

			return bloodPressureMeasurement;
		}

		return null;
	}

	public BloodPressureMeasurementDto toBloodPressureMeasurementDto(BloodPressureMeasurement bloodPressureMeasurement) {
		if (bloodPressureMeasurement != null) {
			BloodPressureMeasurementDto bloodPressureMeasurementDto = new BloodPressureMeasurementDto();
			bloodPressureMeasurementDto.setId(bloodPressureMeasurement.getId());
			bloodPressureMeasurementDto.setComments(bloodPressureMeasurement.getComments());
			bloodPressureMeasurementDto.setDate(bloodPressureMeasurement.getDateTaken());
			bloodPressureMeasurementDto.setDiastolic(bloodPressureMeasurement.getDiastolic());
			bloodPressureMeasurementDto.setSystolic(bloodPressureMeasurement.getSystolic());
			bloodPressureMeasurementDto.setPersonId(bloodPressureMeasurementDto.getPersonId());

			return bloodPressureMeasurementDto;
		}

		return null;
	}

	public List<BloodPressureMeasurementDto> toBloodPressureMeasurementDtos(List<BloodPressureMeasurement> bloodPressureMeasurements) {
		if (bloodPressureMeasurements != null) {
			List<BloodPressureMeasurementDto> bloodPressureMeasurementDtos = new ArrayList<>();

			for (BloodPressureMeasurement bloodPressureMeasurement : bloodPressureMeasurements) {
				bloodPressureMeasurementDtos.add(toBloodPressureMeasurementDto(bloodPressureMeasurement));
			}

			return bloodPressureMeasurementDtos;
		}

		return null;
	}

	public BloodPressureMeasurement toBloodPressureMeasurement(IHealthBpDataDto iHealthBpDataDto, Person person) {
		if (iHealthBpDataDto != null) {
			BloodPressureMeasurement bloodPressureMeasurement = new BloodPressureMeasurement();
			bloodPressureMeasurement.setDateTaken(IHealthUtils.toDate(iHealthBpDataDto.getMeasurementDate()));
			bloodPressureMeasurement.setDiastolic(iHealthBpDataDto.getDiastolic());
			bloodPressureMeasurement.setSystolic(iHealthBpDataDto.getSystolic());
			bloodPressureMeasurement.setPerson(person);
			bloodPressureMeasurement.setComments(iHealthBpDataDto.getNote());

			return bloodPressureMeasurement;
		}

		return null;
	}

	public List<BloodPressureMeasurement> toBloodPressureMeasurements(List<IHealthBpDataDto> iHealthBpDataDtos, Person person) {
		if (iHealthBpDataDtos != null) {
			List<BloodPressureMeasurement> bloodPressureMeasurements = new ArrayList<>();

			for (IHealthBpDataDto iHealthBpDataDto : iHealthBpDataDtos) {
				bloodPressureMeasurements.add(toBloodPressureMeasurement(iHealthBpDataDto, person));
			}

			return bloodPressureMeasurements;
		}

		return null;
	}
}
