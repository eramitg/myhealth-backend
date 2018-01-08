package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.Person;

public interface IHealthMeasurementsImportService {
    void importWeightMeasurements(String userId, String accessToken, Person person);
    void importBloodPressureMeasurements(String userId, String accessToken, Person person);

}
