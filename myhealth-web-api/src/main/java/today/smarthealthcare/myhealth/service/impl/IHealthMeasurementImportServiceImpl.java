package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.entity.Person;
import today.smarthealthcare.myhealth.ihealth.client.IHealthClient;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthUserBpDataResponseDto;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthUserWeightDataResponseDto;
import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthBpDataDto;
import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthWeightDataDto;
import today.smarthealthcare.myhealth.service.BloodPressureMeasurementService;
import today.smarthealthcare.myhealth.service.IHealthMeasurementsImportService;
import today.smarthealthcare.myhealth.service.WeightMeasurementService;
import today.smarthealthcare.myhealth.service.mapper.BloodPressureMeasurementMapper;
import today.smarthealthcare.myhealth.service.mapper.WeightMeasurementMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IHealthMeasurementImportServiceImpl implements IHealthMeasurementsImportService {
    @Autowired
    private WeightMeasurementService weightMeasurementService;
    @Autowired
    private BloodPressureMeasurementService bloodPressureMeasurementService;
    @Autowired
    private BloodPressureMeasurementMapper bloodPressureMeasurementMapper;
    @Autowired
    private WeightMeasurementMapper weightMeasurementMapper;
    @Autowired
    private IHealthClient iHealthClient;

    @Override
    public void importWeightMeasurements(String userId, String accessToken, Person person) {
        IHealthUserWeightDataResponseDto weightDataFirstPage = iHealthClient.retrieveUserWeightData(userId, accessToken);

        List<IHealthUserWeightDataResponseDto> userWeightData = Lists.newArrayList(weightDataFirstPage);
        String nextPageUrl = weightDataFirstPage.getNextPageUrl();

        while (StringUtils.isNotEmpty(nextPageUrl)) {
            IHealthUserWeightDataResponseDto currentPage = iHealthClient.retrieveWeightDataByUrl(nextPageUrl);
            nextPageUrl = currentPage.getNextPageUrl();
            userWeightData.add(currentPage);
        }

        List<IHealthWeightDataDto> iHealthWeightDataDtos = new ArrayList<>();

        for (IHealthUserWeightDataResponseDto iHealthUserWeightDataResponseDto : userWeightData) {
            iHealthWeightDataDtos.addAll(iHealthUserWeightDataResponseDto.getWeightData());
        }

        weightMeasurementService.save(weightMeasurementMapper.toWeightMeasurements(iHealthWeightDataDtos, person));

    }

    @Override
    public void importBloodPressureMeasurements(String userId, String accessToken, Person person) {
        IHealthUserBpDataResponseDto firstPage = iHealthClient.retrieveUserBpData(userId, accessToken);

        List<IHealthUserBpDataResponseDto> userBpData = Lists.newArrayList(firstPage);
        String nextPageUrl = firstPage.getNextPageUrl();

        while (StringUtils.isNotEmpty(nextPageUrl)) {
            IHealthUserBpDataResponseDto currentPage = iHealthClient.retrieveBpDataByUrl(nextPageUrl);
            nextPageUrl = currentPage.getNextPageUrl();
            userBpData.add(currentPage);
        }

        List<IHealthBpDataDto> iHealthBpDataDtos = new ArrayList<>();

        for (IHealthUserBpDataResponseDto iHealthUserBpDataResponseDto : userBpData) {
            iHealthBpDataDtos.addAll(iHealthUserBpDataResponseDto.getBpData());
        }

        bloodPressureMeasurementService.save(bloodPressureMeasurementMapper.toBloodPressureMeasurements(iHealthBpDataDtos, person));
    }
}
