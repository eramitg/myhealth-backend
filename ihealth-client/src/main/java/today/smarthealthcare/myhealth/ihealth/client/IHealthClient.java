package today.smarthealthcare.myhealth.ihealth.client;


import today.smarthealthcare.myhealth.ihealth.config.IHealthProperties;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthAccessTokenResponseDto;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthUserBoDataResponseDto;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthUserBpDataResponseDto;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthUserWeightDataResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import today.smarthealthcare.myhealth.ihealth.config.IHealthProperties;
import today.smarthealthcare.myhealth.ihealth.dto.IHealthUserBoDataResponseDto;

@Component
public class IHealthClient {
    @Autowired
    @Qualifier("iHealthRestTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private IHealthProperties iHealthProperties;

    public String buildAuthorizationUrl(Long patientId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(iHealthProperties.getUserAuthorizationUrl());
        builder.queryParams(createAuthorizationParameters(patientId));

        return builder.build().toString();
    }

    public IHealthAccessTokenResponseDto retrieveAccessToken(String code) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(iHealthProperties.getAccessTokenEndpoint());
        builder.queryParams(createAccessTokenRequestParameters(code));

        return restTemplate.getForObject(builder.build().toString(), IHealthAccessTokenResponseDto.class);
    }

    public IHealthUserBpDataResponseDto retrieveUserBpData(String userId, String accessToken) {
        String bpUrl = iHealthProperties.getUserDataEndpoint() + userId + "/bp.json/";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(bpUrl);

        MultiValueMap<String, String> userDataParams = createUserDataRequestParameters(accessToken);
        userDataParams.add("sc", iHealthProperties.getBloodPressureSc());
        userDataParams.add("sv", iHealthProperties.getBloodPressureSv());

        uriComponentsBuilder.queryParams(userDataParams);

        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), IHealthUserBpDataResponseDto.class);
    }

    public IHealthUserBpDataResponseDto retrieveBpDataByUrl(String url) {
        return restTemplate.getForObject(url, IHealthUserBpDataResponseDto.class);
    }

    public IHealthUserBoDataResponseDto retrieveUserBoData(String userId, String accessToken) {
        String bpUrl = iHealthProperties.getUserDataEndpoint() + userId + "/spo2.json/";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(bpUrl);

        MultiValueMap<String, String> userDataParams = createUserDataRequestParameters(accessToken);
        userDataParams.add("sc", iHealthProperties.getBloodOxygenSc());
        userDataParams.add("sv", iHealthProperties.getBloodOxygenSv());

        uriComponentsBuilder.queryParams(userDataParams);

        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), IHealthUserBoDataResponseDto.class);
    }

    public IHealthUserBoDataResponseDto retrieveBoDataByUrl(String url) {
        return restTemplate.getForObject(url, IHealthUserBoDataResponseDto.class);
    }

    public IHealthUserWeightDataResponseDto retrieveUserWeightData(String userId, String accessToken) {
        String bpUrl = iHealthProperties.getUserDataEndpoint() + userId + "/weight.json/";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(bpUrl);

        MultiValueMap<String, String> userDataParams = createUserDataRequestParameters(accessToken);
        userDataParams.add("sc", iHealthProperties.getWeightSc());
        userDataParams.add("sv", iHealthProperties.getWeightSv());

        uriComponentsBuilder.queryParams(userDataParams);

        return restTemplate.getForObject(uriComponentsBuilder.build().toString(), IHealthUserWeightDataResponseDto.class);
    }

    public IHealthUserWeightDataResponseDto retrieveWeightDataByUrl(String url) {
        return restTemplate.getForObject(url, IHealthUserWeightDataResponseDto.class);
    }

    private MultiValueMap<String, String> createAuthorizationParameters(Long patientId) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", iHealthProperties.getClientId());
        map.add("response_type", iHealthProperties.getAuthorizationResponseType());
        map.add("redirect_uri", iHealthProperties.getRedirectForCode() + "?id=" + String.valueOf(patientId));
        map.add("APIName", new StringBuilder(iHealthProperties.getWeightApiName()).toString());

        return map;
    }

    private MultiValueMap<String, String> createAccessTokenRequestParameters(String code) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", iHealthProperties.getClientId());
        map.add("client_secret", iHealthProperties.getClientSecret());
        map.add("grant_type", iHealthProperties.getGrantType());
        map.add("code", code);
        map.add("redirect_uri", iHealthProperties.getRedirectForAccessToken());

        return map;
    }

    private MultiValueMap<String, String> createUserDataRequestParameters(String accessToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", iHealthProperties.getClientId());
        map.add("client_secret", iHealthProperties.getClientSecret());
        map.add("access_token", accessToken);

        return map;
    }
}
