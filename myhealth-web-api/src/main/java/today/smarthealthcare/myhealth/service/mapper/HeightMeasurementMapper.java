package today.smarthealthcare.myhealth.service.mapper;

import today.smarthealthcare.myhealth.dto.HeightMeasurementDto;
import today.smarthealthcare.myhealth.entity.HeightMeasurement;
import today.smarthealthcare.myhealth.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeightMeasurementMapper {
	@Autowired
	private PersonService personService;

	public HeightMeasurement toHeightMeasurement(HeightMeasurementDto heightMeasurementDto) {
		if (heightMeasurementDto != null) {
			HeightMeasurement heightMeasurement = new HeightMeasurement();
			heightMeasurement.setId(heightMeasurementDto.getId());
			heightMeasurement.setComments(heightMeasurementDto.getComments());
			heightMeasurement.setDateTaken(heightMeasurementDto.getDate());
			heightMeasurement.setValue(heightMeasurementDto.getValue());
			heightMeasurement.setPerson(personService.findById(heightMeasurementDto.getPersonId()));

			return heightMeasurement;
		}

		return null;
	}

	public HeightMeasurementDto toHeightMeasurementDto(HeightMeasurement heightMeasurement) {
		if (heightMeasurement != null) {
			HeightMeasurementDto heightMeasurementDto = new HeightMeasurementDto();
			heightMeasurementDto.setPersonId(heightMeasurement.getPerson().getId());
			heightMeasurementDto.setValue(heightMeasurement.getValue());
			heightMeasurementDto.setComments(heightMeasurement.getComments());
			heightMeasurementDto.setDate(heightMeasurement.getDateTaken());
			heightMeasurementDto.setId(heightMeasurement.getId());

			return heightMeasurementDto;
		}

		return null;
	}

	public List<HeightMeasurementDto> toHeightHeasurementDtos(List<HeightMeasurement> heightMeasurements) {
		if (heightMeasurements != null) {
			List<HeightMeasurementDto> heightMeasurementDtos = new ArrayList<>();

			for (HeightMeasurement heightMeasurement : heightMeasurements) {
				heightMeasurementDtos.add(toHeightMeasurementDto(heightMeasurement));
			}

			return heightMeasurementDtos;
		}

		return null;
	}
}
