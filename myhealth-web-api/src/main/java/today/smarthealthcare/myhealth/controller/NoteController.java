package today.smarthealthcare.myhealth.controller;

import today.smarthealthcare.myhealth.dto.NoteDto;
import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.service.MyHealthUserService;
import today.smarthealthcare.myhealth.service.NoteService;
import today.smarthealthcare.myhealth.service.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
	@Autowired
	private NoteService noteService;
	@Autowired
	private NoteMapper noteMapper;
	@Autowired
	private MyHealthUserService myHealthUserService;

	@RequestMapping(value = "/rest/note", method = RequestMethod.POST)
	@ResponseBody
	public NoteDto saveNote(@RequestBody NoteDto noteDto) {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		noteDto.setPersonId(myHealthUser.getPersons().get(0).getId());

		return noteMapper.toNoteDto(noteService.save(noteMapper.toNote(noteDto)));
	}

	@RequestMapping("/rest/notes")
	@ResponseBody
	public List<NoteDto> findAllNotesByPerson() {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return noteMapper.toNoteDtos(noteService.findByPerson(myHealthUser.getPersons().get(0)));
	}
}
