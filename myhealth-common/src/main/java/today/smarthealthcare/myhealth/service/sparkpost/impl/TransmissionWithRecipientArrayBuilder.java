package today.smarthealthcare.myhealth.service.sparkpost.impl;

import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionWithRecipientArrayBuilder {
    private String campaignId;
    private List<RecipientAttributes> recipientArray;
    private TemplateContentAttributes templateContentAttributes;

    public TransmissionWithRecipientArrayBuilder withCampaignId(String campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public TransmissionWithRecipientArrayBuilder withRecipientArray(List<RecipientAttributes> recipientArray) {
        this.recipientArray = recipientArray;
        return this;
    }

    public TransmissionWithRecipientArrayBuilder withTemplateContentAttribute(TemplateContentAttributes templateContentAttribute) {
        this.templateContentAttributes = templateContentAttribute;
        return this;
    }

    public TransmissionWithRecipientArray build() {
        TransmissionWithRecipientArray transmissionWithRecipientArray = new TransmissionWithRecipientArray();
        transmissionWithRecipientArray.setRecipientArray(recipientArray);
        transmissionWithRecipientArray.setContentAttributes(templateContentAttributes);
        transmissionWithRecipientArray.setCampaignId(campaignId);

        return transmissionWithRecipientArray;
    }
}
