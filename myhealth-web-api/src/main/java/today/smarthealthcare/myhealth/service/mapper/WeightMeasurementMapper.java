package today.smarthealthcare.myhealth.service.mapper;

import today.smarthealthcare.myhealth.dto.WeightMeasurementDto;
import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.entity.WeightMeasurement;
import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthBpDataDto;
import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthWeightDataDto;
import today.smarthealthcare.myhealth.ihealth.util.IHealthUtils;
import today.smarthealthcare.myhealth.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeightMeasurementMapper {
	@Autowired
	private PersonService personService;

	public WeightMeasurement toWeightMeasurement(WeightMeasurementDto weightMeasurementDto) {
		if (weightMeasurementDto != null) {
			WeightMeasurement weightMeasurement = new WeightMeasurement();
			weightMeasurement.setId(weightMeasurementDto.getId());
			weightMeasurement.setComments(weightMeasurementDto.getComments());
			weightMeasurement.setDateTaken(weightMeasurementDto.getDate());
			weightMeasurement.setValue(weightMeasurementDto.getValue());
			weightMeasurement.setPerson(personService.findById(weightMeasurementDto.getPersonId()));

			return weightMeasurement;
		}

		return null;
	}

	public WeightMeasurementDto toWeightMeasurementDto(WeightMeasurement weightMeasurement) {
		if (weightMeasurement != null) {
			WeightMeasurementDto weightMeasurementDto = new WeightMeasurementDto();
			weightMeasurementDto.setPersonId(weightMeasurement.getPerson().getId());
			weightMeasurementDto.setValue(weightMeasurement.getValue());
			weightMeasurementDto.setComments(weightMeasurement.getComments());
			weightMeasurementDto.setDate(weightMeasurement.getDateTaken());
			weightMeasurementDto.setId(weightMeasurement.getId());

			return weightMeasurementDto;
		}

		return null;
	}

	public List<WeightMeasurementDto> toWeightMeasurementDtos(List<WeightMeasurement> weightMeasurements) {
		if (weightMeasurements != null) {
			List<WeightMeasurementDto> weightMeasurementDtos = new ArrayList<>();

			for (WeightMeasurement weightMeasurement : weightMeasurements) {
				weightMeasurementDtos.add(toWeightMeasurementDto(weightMeasurement));
			}

			return weightMeasurementDtos;
		}

		return null;
	}

	public WeightMeasurement toWeighttMeasurement(IHealthWeightDataDto iHealthWeightDataDto, Person person) {
		if (iHealthWeightDataDto != null) {
			WeightMeasurement weightMeasurement = new WeightMeasurement();
			weightMeasurement.setDateTaken(IHealthUtils.toDate(iHealthWeightDataDto.getMeasurementDate()));
			weightMeasurement.setValue(iHealthWeightDataDto.getWeightValue());
			weightMeasurement.setPerson(person);

			return weightMeasurement;
		}

		return null;
	}

	public List<WeightMeasurement> toWeightMeasurements(List<IHealthWeightDataDto> iHealthWeightDataDtos, Person person) {
		if (iHealthWeightDataDtos != null) {
			List<WeightMeasurement> weightMeasurements = new ArrayList<>();

			for (IHealthWeightDataDto iHealthWeightDataDto : iHealthWeightDataDtos) {
				weightMeasurements.add(toWeighttMeasurement(iHealthWeightDataDto, person));
			}

			return weightMeasurements;
		}

		return null;
	}
}
