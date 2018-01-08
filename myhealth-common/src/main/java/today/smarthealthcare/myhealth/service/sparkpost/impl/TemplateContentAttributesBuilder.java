package today.smarthealthcare.myhealth.service.sparkpost.impl;

import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.AttachmentAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateContentAttributesBuilder {
    private String template;
    private String subject;
    private AddressAttributes from;
    private List<AttachmentAttributes> attachments;

    public TemplateContentAttributesBuilder withTemplate(String template) {
        this.template = template;
        return this;
    }

    public TemplateContentAttributesBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public TemplateContentAttributesBuilder withFrom(AddressAttributes from) {
        this.from = from;
        return this;
    }

    public TemplateContentAttributesBuilder withAttachments(List<AttachmentAttributes> attachments) {
        this.attachments = attachments;
        return this;
    }


    public TemplateContentAttributes build() {
        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
        contentAttributes.setFrom(from);
        contentAttributes.setSubject(subject);
        //TODO Check if template id is needed also in a content
        contentAttributes.setTemplateId(template);
        contentAttributes.setAttachments(attachments);

        return contentAttributes;
    }

}
