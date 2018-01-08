package today.smarthealthcare.myhealth.service.mapper;

import today.smarthealthcare.myhealth.dto.PersonDto;
import today.smarthealthcare.myhealth.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
	public Person toPerson(PersonDto personDto) {
		if (personDto != null) {
			Person person = new Person();
			person.setId(personDto.getId());
			person.setDateOfBirth(personDto.getDateOfBirth());
			person.setName(personDto.getName());
			person.setSurname(personDto.getSurname());
			person.setGender(personDto.getGender());

			return person;
		}

		return null;
	}

	public PersonDto toPersonDto(Person person) {
		if (person != null) {
			PersonDto personDto = new PersonDto();
			personDto.setId(person.getId());
			personDto.setDateOfBirth(person.getDateOfBirth());
			personDto.setName(person.getName());
			personDto.setSurname(person.getSurname());
			personDto.setGender(person.getGender());

			return personDto;
		}

		return null;
	}
}
