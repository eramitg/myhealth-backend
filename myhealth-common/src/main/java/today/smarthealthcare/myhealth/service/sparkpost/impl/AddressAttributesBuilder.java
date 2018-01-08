package today.smarthealthcare.myhealth.service.sparkpost.impl;

import com.sparkpost.model.AddressAttributes;
import org.springframework.stereotype.Service;

@Service
public class AddressAttributesBuilder {
    private String email;
    private String name;

    public AddressAttributesBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressAttributesBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AddressAttributes build() {
        AddressAttributes addressAttributes = new AddressAttributes();
        addressAttributes.setName(name);
        addressAttributes.setEmail(email);

        return addressAttributes;
    }
}
