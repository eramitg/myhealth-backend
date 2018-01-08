package today.smarthealthcare.myhealth.controller;

import today.smarthealthcare.myhealth.dto.IHealthAuthUrlDto;
import today.smarthealthcare.myhealth.dto.UserDto;
import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.ihealth.client.IHealthClient;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthAccessTokenResponseDto;
import today.smarthealthcare.myhealth.service.IHealthMeasurementsImportService;
import today.smarthealthcare.myhealth.service.MyHealthUserService;
import today.smarthealthcare.myhealth.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IHealthController {
	@Autowired
	private IHealthClient iHealthClient;
	@Autowired
	private PersonService personService;
	@Autowired
	private IHealthMeasurementsImportService iHealthMeasurementsImportService;
	@Autowired
	private MyHealthUserService myHealthUserService;

	@RequestMapping(value = "/rest/ihealth/authorizationUrl", method = RequestMethod.POST)
	@ResponseBody
	public IHealthAuthUrlDto getAuthorizationUrlString(@RequestBody UserDto userDto) {
		MyHealthUser myHealthUser = myHealthUserService.findByEmail(userDto.getEmail());
		return new IHealthAuthUrlDto(iHealthClient.buildAuthorizationUrl(myHealthUser.getPersons().get(0).getId()));
	}

	@RequestMapping(value = "/rest/ihealth/redirect_for_code", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void handleIHealthCodeResponse(@RequestParam String code, @RequestParam Long id) {
		Person person = personService.findById(id);

		IHealthAccessTokenResponseDto accessTokenResponseDto = iHealthClient.retrieveAccessToken(code);

		iHealthMeasurementsImportService.importWeightMeasurements(
				accessTokenResponseDto.getUserID(), accessTokenResponseDto.getAccessToken(), person);
		iHealthMeasurementsImportService.importBloodPressureMeasurements(
				accessTokenResponseDto.getUserID(), accessTokenResponseDto.getAccessToken(), person);
	}
}
