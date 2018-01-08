package today.smarthealthcare.myhealth.controller;

import today.smarthealthcare.myhealth.dto.RegistrationFormDto;
import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.exception.FieldValidationException;
import today.smarthealthcare.myhealth.exception.UserAlreadyRegisteredException;
import today.smarthealthcare.myhealth.service.MyHealthUserActivationTokenService;
import today.smarthealthcare.myhealth.service.MyHealthUserService;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {
	@Autowired
	private MyHealthUserService myHealthUserService;
	@Autowired
	private MyHealthUserActivationTokenService myHealthUserActivationTokenService;

	@RequestMapping(value = "/rest/registration", method = RequestMethod.POST)
	public void registerUser(@RequestBody @Valid RegistrationFormDto registrationFormDto, BindingResult bindingResult) throws SparkPostException {
		if (bindingResult.hasErrors()) {
			throw new FieldValidationException(bindingResult.getFieldErrors());
		}

		MyHealthUser existingMyHealthUser = myHealthUserService.findByEmail(registrationFormDto.getEmail());

		if (existingMyHealthUser == null) {
			myHealthUserService.register(registrationFormDto.getEmail());
		} else {
			throw new UserAlreadyRegisteredException();
		}
	}
}
