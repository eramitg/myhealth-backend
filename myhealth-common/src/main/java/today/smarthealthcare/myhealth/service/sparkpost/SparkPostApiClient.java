package today.smarthealthcare.myhealth.service.sparkpost;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TemplateRetrieveResponse;

public interface SparkPostApiClient {
    TemplateRetrieveResponse retrieveTemplate(String template) throws SparkPostException;
    Response sendTransmission(TransmissionWithRecipientArray transmission) throws SparkPostException;
}
