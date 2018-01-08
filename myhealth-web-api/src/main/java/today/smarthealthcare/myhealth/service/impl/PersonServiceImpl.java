package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.repository.PersonRepository;
import today.smarthealthcare.myhealth.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import today.smarthealthcare.myhealth.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person findById(long id) {
		return personRepository.findById(id);
	}
}
