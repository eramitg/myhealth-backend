package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.Person;

public interface PersonService {
	Person save(Person person);
	Person findById(long id);
}
