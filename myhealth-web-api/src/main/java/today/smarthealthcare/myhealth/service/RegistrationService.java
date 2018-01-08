package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import com.sparkpost.exception.SparkPostException;

public interface RegistrationService {
	void sendRegistrationConfirmationEmail(MyHealthUser myHealthUser) throws SparkPostException;
}
