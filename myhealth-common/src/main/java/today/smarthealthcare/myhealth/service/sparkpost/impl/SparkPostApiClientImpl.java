package today.smarthealthcare.myhealth.service.sparkpost.impl;

import today.smarthealthcare.myhealth.service.sparkpost.SparkPostApiClient;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TemplateRetrieveResponse;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.transport.RestConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkPostApiClientImpl implements SparkPostApiClient {
    @Autowired
    private RestConnection restConnection;

    public static final int NUMBER_OF_RESPONSE_ERRORS = 0;
    public static final boolean DRAFT_TEMPLATE = true;

    @Override
    public TemplateRetrieveResponse retrieveTemplate(String template) throws SparkPostException {
        return ResourceTemplates.retrieve(restConnection, template, DRAFT_TEMPLATE);
    }

    @Override
    public Response sendTransmission(TransmissionWithRecipientArray transmission) throws SparkPostException {
        return ResourceTransmissions.create(restConnection, NUMBER_OF_RESPONSE_ERRORS, transmission);
    }
}
