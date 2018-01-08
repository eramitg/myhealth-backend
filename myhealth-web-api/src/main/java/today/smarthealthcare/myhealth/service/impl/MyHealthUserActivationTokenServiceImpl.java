package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.MyHealthUserActivationToken;
import today.smarthealthcare.myhealth.repository.MyHealthUserActivationTokenRepository;
import today.smarthealthcare.myhealth.service.MyHealthUserActivationTokenService;
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

import today.smarthealthcare.myhealth.repository.MyHealthUserActivationTokenRepository;

@Service
public class MyHealthUserActivationTokenServiceImpl implements MyHealthUserActivationTokenService {
	@Autowired
	private MyHealthUserActivationTokenRepository tokenRepository;
	@Autowired
	private SparkPostApiClient sparkPostApiClient;
	@Autowired
	private TransmissionWithRecipientArrayBuilder transmissionWithRecipientArrayBuilder;
	@Autowired
	private RecipientAttributesBuilder recipientAttributesBuilder;
	@Autowired
	private AddressAttributesBuilder addressAttributesBuilder;

	@Value("${activation-token.validation.time}")
	private int validationMins;
	@Value("${webapp.url}")
	private  String webappUrl;

	@Override
	public MyHealthUserActivationToken findByToken(String token) {
		return tokenRepository.findByToken(token);
	}

	@Override
	public boolean isTokenValid(MyHealthUserActivationToken token) {
		return (token != null) && (token.isActive() && new Date().before(token.getValidTill()));
	}

	@Override
	public MyHealthUserActivationToken generateToken(String email) {
		MyHealthUserActivationToken myHealthUserActivationToken = new MyHealthUserActivationToken();
		myHealthUserActivationToken.setToken(Hashing.sha256().hashString(String.format("%s%s", email, System.currentTimeMillis()), Charsets.UTF_8).toString());
		myHealthUserActivationToken.setEmail(email);
		myHealthUserActivationToken.setActive(true);

		DateTime validTill = new DateTime(myHealthUserActivationToken.getCreated()).plusMinutes(validationMins);
		myHealthUserActivationToken.setValidTill(validTill.toDate());

		return myHealthUserActivationToken;
	}

	@Override
	public void save(MyHealthUserActivationToken myHealthUserActivationToken) {
		tokenRepository.save(myHealthUserActivationToken);
	}

	@Override
	public void sendActivationTokenByEmail(MyHealthUserActivationToken myHealthUserActivationToken) throws SparkPostException {
		Map<String, Object> substitutionData = new HashMap<>();
		substitutionData.put("activation_link", webappUrl + "/#/activation/" + myHealthUserActivationToken.getToken());
		TransmissionWithRecipientArray transmissionWithRecipientArray = transmissionWithRecipientArrayBuilder
				.withRecipientArray(Lists.newArrayList(recipientAttributesBuilder
						.withAddress(addressAttributesBuilder
								.withEmail(myHealthUserActivationToken.getEmail())
								.build())
						.withSubstitutionData(substitutionData)
						.build()))
				.withCampaignId("activation")
				.build();

		TemplateContentAttributes template = new TemplateContentAttributes();
		template.setTemplateId("activation");
		template.setUseDraftTemplate(true);
		transmissionWithRecipientArray.setContentAttributes(template);

		sparkPostApiClient.sendTransmission(transmissionWithRecipientArray);
	}
}
