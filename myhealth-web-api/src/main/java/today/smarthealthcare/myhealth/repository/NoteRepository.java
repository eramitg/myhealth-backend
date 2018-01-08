package today.smarthealthcare.myhealth.repository;

import today.smarthealthcare.myhealth.entity.Note;
import today.smarthealthcare.myhealth.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>{
	List<Note> findByPerson(Person person);
}
