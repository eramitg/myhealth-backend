package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.service.RegistrationService;
import today.smarthealthcare.myhealth.service.sparkpost.SparkPostApiClient;
import today.smarthealthcare.myhealth.service.sparkpost.impl.AddressAttributesBuilder;
import today.smarthealthcare.myhealth.service.sparkpost.impl.RecipientAttributesBuilder;
import today.smarthealthcare.myhealth.service.sparkpost.impl.TransmissionWithRecipientArrayBuilder;
import com.google.common.collect.Lists;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private TransmissionWithRecipientArrayBuilder transmissionWithRecipientArrayBuilder;
	@Autowired
	private RecipientAttributesBuilder recipientAttributesBuilder;
	@Autowired
	private AddressAttributesBuilder addressAttributesBuilder;
	@Autowired
	private SparkPostApiClient sparkPostApiClient;

	@Value("${webapp.url}")
	private String webappUrl;

	@Override
	public void sendRegistrationConfirmationEmail(MyHealthUser myHealthUser) throws SparkPostException {
		if (myHealthUser != null) {
			Map<String, Object> substitutionData = new HashMap<>();
			substitutionData.put("login_link", webappUrl + "/#/login/");
			TransmissionWithRecipientArray transmissionWithRecipientArray = transmissionWithRecipientArrayBuilder
					.withRecipientArray(Lists.newArrayList(recipientAttributesBuilder
							.withAddress(addressAttributesBuilder
									.withEmail(myHealthUser.getEmail())
									.build())
							.withSubstitutionData(substitutionData)
							.build()))
					.withCampaignId("registration-confirmation")
					.build();

			TemplateContentAttributes template = new TemplateContentAttributes();
			template.setTemplateId("registration-confirmation");
			template.setUseDraftTemplate(true);
			transmissionWithRecipientArray.setContentAttributes(template);

			sparkPostApiClient.sendTransmission(transmissionWithRecipientArray);
		}
	}
}
