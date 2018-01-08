package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.Note;
import today.smarthealthcare.myhealth.entity.Person;

import java.util.List;

import today.smarthealthcare.myhealth.entity.Note;
import today.smarthealthcare.myhealth.entity.Person;

public interface NoteService {
	List<Note> findByPerson(Person person);
	Note save(Note note);
}
