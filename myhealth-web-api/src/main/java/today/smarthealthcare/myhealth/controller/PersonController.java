package today.smarthealthcare.myhealth.controller;

import today.smarthealthcare.myhealth.dto.PersonDto;
import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.service.MyHealthUserService;
import today.smarthealthcare.myhealth.service.PersonService;
import today.smarthealthcare.myhealth.service.mapper.PersonMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private MyHealthUserService myHealthUserService;

	@RequestMapping(value = "/rest/person", method = RequestMethod.POST)
	@ResponseBody
	public PersonDto saveProfile(@RequestBody PersonDto personDto) {
		String userEmail =  SecurityContextHolder.getContext().getAuthentication().getName();
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(userEmail);
		myHealthUser.setPersons(Lists.newArrayList(personMapper.toPerson(personDto)));
		MyHealthUser savedUser = myHealthUserService.save(myHealthUser);

		return personMapper.toPersonDto(savedUser.getPersons().get(0));

	}

	@RequestMapping("/rest/person")
	@ResponseBody
	//TODO Aleksandr Kormiltsyn - Now only 1 person can be assigned to the user. In future there are should be a list of assigned persons like user itself, children, etc
	public PersonDto findProfileByUserEmail() {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		if (!CollectionUtils.isEmpty(myHealthUser.getPersons())) {
			return personMapper.toPersonDto(myHealthUser.getPersons().get(0));
		}

		return null;
	}

}
