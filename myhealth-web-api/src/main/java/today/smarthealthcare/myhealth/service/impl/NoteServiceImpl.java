package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.Note;
import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.repository.NoteRepository;
import today.smarthealthcare.myhealth.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import today.smarthealthcare.myhealth.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteRepository noteRepository;

	@Override
	public List<Note> findByPerson(Person person) {
		if (person != null) {
			return noteRepository.findByPerson(person);
		}

		return null;
	}

	@Override
	public Note save(Note note) {
		return noteRepository.save(note);
	}
}
