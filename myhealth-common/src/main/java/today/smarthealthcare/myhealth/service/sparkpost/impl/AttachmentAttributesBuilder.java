package today.smarthealthcare.myhealth.service.sparkpost.impl;

import com.sparkpost.model.AttachmentAttributes;
import org.springframework.stereotype.Service;

@Service
public class AttachmentAttributesBuilder {
    private String name;
    private String type;
    private String data;

    public AttachmentAttributesBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AttachmentAttributesBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public AttachmentAttributesBuilder withData(String data) {
        this.data = data;
        return this;
    }

    public AttachmentAttributes build() {
        AttachmentAttributes attachmentAttributes = new AttachmentAttributes();
        attachmentAttributes.setType(type);
        attachmentAttributes.setName(name);
        attachmentAttributes.setData(data);

        return attachmentAttributes;
    }
}
