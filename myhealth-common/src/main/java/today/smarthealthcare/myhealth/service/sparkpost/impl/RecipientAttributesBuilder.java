package today.smarthealthcare.myhealth.service.sparkpost.impl;

import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.RecipientAttributes;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipientAttributesBuilder {
    private AddressAttributes address;
    private Map<String, Object> substitutionData;

    public RecipientAttributesBuilder withAddress(AddressAttributes address) {
        this.address = address;
        return this;
    }

    public RecipientAttributesBuilder withSubstitutionData(Map<String, Object> params) {
        this.substitutionData = params;
        return this;
    }

    public RecipientAttributes build() {
        RecipientAttributes recipient = new RecipientAttributes();
        recipient.setSubstitutionData(substitutionData);
        recipient.setAddress(address);

        return recipient;
    }
}
