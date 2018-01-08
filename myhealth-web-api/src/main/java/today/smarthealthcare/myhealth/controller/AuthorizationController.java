package today.smarthealthcare.myhealth.controller;

import today.smarthealthcare.myhealth.dto.ActivationFormDto;
import today.smarthealthcare.myhealth.dto.ResetPasswordFormDto;
import today.smarthealthcare.myhealth.dto.ResetPasswordResponseDto;
import today.smarthealthcare.myhealth.dto.RestorePasswordFormDto;
import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.entity.MyHealthUserActivationToken;
import today.smarthealthcare.myhealth.entity.PasswordResetToken;
import today.smarthealthcare.myhealth.exception.TokenNotValidException;
import today.smarthealthcare.myhealth.exception.ValidationException;
import today.smarthealthcare.myhealth.service.MyHealthUserActivationTokenService;
import today.smarthealthcare.myhealth.service.MyHealthUserService;
import today.smarthealthcare.myhealth.service.PasswordResetTokenService;
import today.smarthealthcare.myhealth.service.RegistrationService;
import today.smarthealthcare.myhealth.utils.PasswordUtils;
import com.sparkpost.exception.SparkPostException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorizationController {

	@Autowired
	private MyHealthUserActivationTokenService myHealthUserActivationTokenService;
	@Autowired
	private MyHealthUserService myHealthUserService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@RequestMapping(value = "rest/activate", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void activateUserAccount(@RequestBody @Valid ActivationFormDto activationFormDto, BindingResult bindingResult) throws SparkPostException {
		if (bindingResult.hasErrors()) {
			throw new ValidationException();
		}

		MyHealthUserActivationToken token = myHealthUserActivationTokenService.findByToken(activationFormDto.getHash());
		if (myHealthUserActivationTokenService.isTokenValid(token)) {
			MyHealthUser myHealthUser = myHealthUserService.findByEmail(token.getEmail());
			myHealthUser.setActive(true);
			String passwordSalt = PasswordUtils.generateSalt();
			myHealthUser.setPasswordSalt(passwordSalt);
			myHealthUser.setPasswordHash(PasswordUtils.getHash(passwordSalt, activationFormDto.getPassword()));

			myHealthUserService.save(myHealthUser);

			registrationService.sendRegistrationConfirmationEmail(myHealthUser);
		} else {
			throw new TokenNotValidException();
		}
	}

	@RequestMapping("/rest/activation/validate/{hash}")
	@ResponseStatus(HttpStatus.OK)
	public void validateActivationToken(@PathVariable String hash) {
		MyHealthUserActivationToken token = myHealthUserActivationTokenService.findByToken(hash);

		if (!myHealthUserActivationTokenService.isTokenValid(token)) {
			throw new TokenNotValidException();
		}
	}

	@RequestMapping(value = "/rest/password/restore", method = RequestMethod.POST)
	public void restorePassword(@RequestBody @Valid RestorePasswordFormDto restorePasswordFormDto, BindingResult result) throws SparkPostException {
		if (result.hasErrors()) {
			throw new ValidationException();
		}

		MyHealthUser myHealthUser = myHealthUserService.findByEmail(restorePasswordFormDto.getEmail());
		myHealthUserService.restorePassword(myHealthUser);
	}

	@RequestMapping("/rest/password/reset/validate")
	@ResponseStatus(HttpStatus.OK)
	public void resetPasswordCheck(@RequestParam String hash) {
		PasswordResetToken token = passwordResetTokenService.findByToken(hash);
		if (!passwordResetTokenService.isTokenValid(token)) {
			throw new ValidationException();
		}
	}

	@RequestMapping("/rest/password/reset/execute")
	public ResetPasswordResponseDto resetPassword(@RequestBody @Valid ResetPasswordFormDto form, BindingResult result) throws SparkPostException {
		if (!result.hasErrors() && form != null && StringUtils.equals(form.getNewPassword(), form.getNewPasswordConfirmation())) {
			PasswordResetToken token = passwordResetTokenService.findByToken(form.getHash());

			if (token != null) {
				MyHealthUser user = myHealthUserService.findByEmail(token.getEmail());
				myHealthUserService.resetPassword(user, form.getNewPassword());

				ResetPasswordResponseDto resetPasswordResponseDto = new ResetPasswordResponseDto();
				resetPasswordResponseDto.setEmail(user.getEmail());
				return resetPasswordResponseDto;
			}

			return null;
		} else {
			throw new ValidationException();
		}
	}
}
