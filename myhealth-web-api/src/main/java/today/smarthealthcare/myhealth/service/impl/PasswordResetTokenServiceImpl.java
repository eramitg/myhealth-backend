package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.PasswordResetToken;
import today.smarthealthcare.myhealth.repository.PasswordResetTokenRepository;
import today.smarthealthcare.myhealth.service.PasswordResetTokenService;
import today.smarthealthcare.myhealth.service.sparkpost.SparkPostApiClient;
import today.smarthealthcare.myhealth.service.sparkpost.impl.AddressAttributesBuilder;
import today.smarthealthcare.myhealth.service.sparkpost.impl.RecipientAttributesBuilder;
import today.smarthealthcare.myhealth.service.sparkpost.impl.TransmissionWithRecipientArrayBuilder;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import today.smarthealthcare.myhealth.repository.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired
	private SparkPostApiClient sparkPostApiClient;
	@Autowired
	private TransmissionWithRecipientArrayBuilder transmissionWithRecipientArrayBuilder;
	@Autowired
	private RecipientAttributesBuilder recipientAttributesBuilder;
	@Autowired
	private AddressAttributesBuilder addressAttributesBuilder;

	@Value("${webapp.url}")
	private  String webappUrl;
	@Value("${activation-token.validation.time}")
	private int validationMins;

	@Override
	public PasswordResetToken findByToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public boolean isTokenValid(PasswordResetToken token) {
		return (token != null) && (token.isActive() && new Date().before(token.getValidTill()));
	}

	@Override
	public PasswordResetToken generateToken(String email) {
		PasswordResetToken token = new PasswordResetToken();
		token.setToken(Hashing.sha256().hashString(String.format("%s%s", email, System.currentTimeMillis()), Charsets.UTF_8).toString());
		token.setEmail(email);
		token.setActive(true);

		DateTime validTill = new DateTime(token.getCreated()).plusMinutes(validationMins);
		token.setValidTill(validTill.toDate());

		return token;
	}

	@Override
	public void save(PasswordResetToken token) {
		passwordResetTokenRepository.save(token);
	}

	@Override
	public void sendPasswordResetTokenByEmail(PasswordResetToken token) throws SparkPostException {
		Map<String, Object> substitutionData = new HashMap<>();
		substitutionData.put("restore_password_link", webappUrl + "/#/reset-password/" + token.getToken());
		TransmissionWithRecipientArray transmissionWithRecipientArray = transmissionWithRecipientArrayBuilder
				.withRecipientArray(Lists.newArrayList(recipientAttributesBuilder
						.withAddress(addressAttributesBuilder
								.withEmail(token.getEmail())
								.build())
						.withSubstitutionData(substitutionData)
						.build()))
				.withCampaignId("restore-password")
				.build();

		TemplateContentAttributes template = new TemplateContentAttributes();
		template.setTemplateId("restore-password");
		template.setUseDraftTemplate(true);
		transmissionWithRecipientArray.setContentAttributes(template);

		sparkPostApiClient.sendTransmission(transmissionWithRecipientArray);
	}
}
