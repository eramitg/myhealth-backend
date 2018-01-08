package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.entity.MyHealthUserActivationToken;
import today.smarthealthcare.myhealth.entity.PasswordResetToken;
import today.smarthealthcare.myhealth.repository.MyHealthUserRepository;
import today.smarthealthcare.myhealth.service.MyHealthUserActivationTokenService;
import today.smarthealthcare.myhealth.service.MyHealthUserService;
import today.smarthealthcare.myhealth.service.PasswordResetTokenService;
import today.smarthealthcare.myhealth.service.sparkpost.SparkPostApiClient;
import today.smarthealthcare.myhealth.service.sparkpost.impl.AddressAttributesBuilder;
import today.smarthealthcare.myhealth.service.sparkpost.impl.RecipientAttributesBuilder;
import today.smarthealthcare.myhealth.service.sparkpost.impl.TransmissionWithRecipientArrayBuilder;
import today.smarthealthcare.myhealth.utils.PasswordUtils;
import com.google.common.collect.Lists;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import today.smarthealthcare.myhealth.repository.MyHealthUserRepository;

@Service
public class MyHealthUserServiceImpl implements MyHealthUserService {
	@Autowired
	private MyHealthUserRepository myHealthUserRepository;
	@Autowired
	private MyHealthUserActivationTokenService myHealthUserActivationTokenService;
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@Override
	public MyHealthUser findByEmail(String email) {
		return myHealthUserRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public void register(String email) throws SparkPostException {
		MyHealthUser myHealthUser = createMyHealthUser(email);
		save(myHealthUser);

		MyHealthUserActivationToken myHealthUserActivationToken = myHealthUserActivationTokenService.generateToken(email);
		myHealthUserActivationTokenService.save(myHealthUserActivationToken);
		myHealthUserActivationTokenService.sendActivationTokenByEmail(myHealthUserActivationToken);
	}

	@Override
	public MyHealthUser save(MyHealthUser myHealthUser) {
		return myHealthUserRepository.save(myHealthUser);
	}

	@Override
	public MyHealthUser createMyHealthUser(String email) {
		MyHealthUser myHealthUser = new MyHealthUser();
		myHealthUser.setEmail(email);
		myHealthUser.setActive(false);
		return myHealthUser;
	}

	@Override
	public void restorePassword(MyHealthUser myHealthUser) throws SparkPostException {
		if (myHealthUser != null) {
			PasswordResetToken passwordResetToken = passwordResetTokenService.generateToken(myHealthUser.getEmail());
			passwordResetTokenService.save(passwordResetToken);
			passwordResetTokenService.sendPasswordResetTokenByEmail(passwordResetToken);
		}
	}

	@Override
	public void resetPassword(MyHealthUser user, String newPassword) {
		if (user!= null && newPassword != null) {
			String salt = PasswordUtils.generateSalt();
			String passwordHash = PasswordUtils.getHash(salt, newPassword);

			user.setPasswordSalt(salt);
			user.setPasswordHash(passwordHash);

			myHealthUserRepository.save(user);
		}
	}
}
