package today.smarthealthcare.myhealth.service.mapper;

import today.smarthealthcare.myhealth.dto.NoteDto;
import today.smarthealthcare.myhealth.entity.Note;
import today.smarthealthcare.myhealth.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteMapper {
	@Autowired
	private PersonService personService;

	public Note toNote(NoteDto noteDto) {
		if (noteDto != null) {
			Note note = new Note();
			note.setId(noteDto.getId());
			note.setDateTaken(noteDto.getDate());
			note.setText(noteDto.getText());
			note.setPerson(personService.findById(noteDto.getPersonId()));

			return note;
		}

		return null;
	}

	public NoteDto toNoteDto(Note note) {
		if (note != null) {
			NoteDto noteDto = new NoteDto();
			noteDto.setId(note.getId());
			noteDto.setText(note.getText());
			noteDto.setDate(note.getDateTaken());
			noteDto.setPersonId(note.getPerson().getId());

			return noteDto;
		}

		return null;
	}

	public List<NoteDto> toNoteDtos(List<Note> notes) {
		if (notes != null) {
			List<NoteDto> noteDtos = new ArrayList<>();

			for (Note note : notes)	{
				noteDtos.add(toNoteDto(note));
			}

			return noteDtos;
		}

		return null;
	}
}
