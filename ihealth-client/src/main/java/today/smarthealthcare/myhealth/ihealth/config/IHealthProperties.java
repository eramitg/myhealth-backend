package today.smarthealthcare.myhealth.ihealth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IHealthProperties {
    @Value("${ihealth.host}")
    private String iHealthHost;
    @Value("${ihealth.userAuthorizationUrl}")
    private String userAuthorizationUrl;
    @Value("${ihealth.accessTokenEndpoint}")
    private String accessTokenEndpoint;
    @Value("${ihealth.userDataEndpoint}")
    private String userDataEndpoint;

    @Value("${ihealth.clientId}")
    private String clientId;
    @Value("${ihealth.clientSecret}")
    private String clientSecret;
    @Value("${ihealth.redirectForCode}")
    private String redirectForCode;
    @Value("${ihealth.redirectForAccessToken}")
    private String redirectForAccessToken;
    @Value("${ihealth.applicationName}")
    private String applicationName;

    @Value("${ihealth.authorizationResponseType}")
    private String authorizationResponseType;
    @Value("${ihealth.grantType}")
    private String grantType;

    @Value("${ihealth.bp.apiName}")
    private String bloodPressureApiName;
    @Value("${ihealth.bp.sc}")
    private String bloodPressureSc;
    @Value("${ihealth.bp.sv}")
    private String bloodPressureSv;

    @Value("${ihealth.bo.apiName}")
    private String bloodOxygenApiName;
    @Value("${ihealth.bo.sc}")
    private String bloodOxygenSc;
    @Value("${ihealth.bo.sv}")
    private String bloodOxygenSv;

    @Value("${ihealth.weight.apiName}")
    private String weightApiName;
    @Value("${ihealth.weight.sc}")
    private String weightSc;
    @Value("${ihealth.weight.sv}")
    private String weightSv;


    public String getiHealthHost() {
        return iHealthHost;
    }

    public void setiHealthHost(String iHealthHost) {
        this.iHealthHost = iHealthHost;
    }

    public String getUserAuthorizationUrl() {
        return iHealthHost + userAuthorizationUrl;
    }

    public void setUserAuthorizationUrl(String userAuthorizationUrl) {
        this.userAuthorizationUrl = userAuthorizationUrl;
    }

    public String getAccessTokenEndpoint() {
        return iHealthHost + accessTokenEndpoint;
    }

    public void setAccessTokenEndpoint(String accessTokenEndpoint) {
        this.accessTokenEndpoint = accessTokenEndpoint;
    }

    public String getUserDataEndpoint() {
        return iHealthHost + userDataEndpoint;
    }

    public void setUserDataEndpoint(String userDataEndpoint) {
        this.userDataEndpoint = userDataEndpoint;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectForCode() {
        return redirectForCode;
    }

    public void setRedirectForCode(String redirectForCode) {
        this.redirectForCode = redirectForCode;
    }

    public String getRedirectForAccessToken() {
        return redirectForAccessToken;
    }

    public void setRedirectForAccessToken(String redirectForAccessToken) {
        this.redirectForAccessToken = redirectForAccessToken;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getBloodPressureApiName() {
        return bloodPressureApiName;
    }

    public void setBloodPressureApiName(String bloodPressureApiName) {
        this.bloodPressureApiName = bloodPressureApiName;
    }

    public String getBloodPressureSc() {
        return bloodPressureSc;
    }

    public void setBloodPressureSc(String bloodPressureSc) {
        this.bloodPressureSc = bloodPressureSc;
    }

    public String getBloodPressureSv() {
        return bloodPressureSv;
    }

    public void setBloodPressureSv(String bloodPressureSv) {
        this.bloodPressureSv = bloodPressureSv;
    }

    public String getAuthorizationResponseType() {
        return authorizationResponseType;
    }

    public void setAuthorizationResponseType(String authorizationResponseType) {
        this.authorizationResponseType = authorizationResponseType;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getBloodOxygenApiName() {
        return bloodOxygenApiName;
    }

    public void setBloodOxygenApiName(String bloodOxygenApiName) {
        this.bloodOxygenApiName = bloodOxygenApiName;
    }

    public String getBloodOxygenSc() {
        return bloodOxygenSc;
    }

    public void setBloodOxygenSc(String bloodOxygenSc) {
        this.bloodOxygenSc = bloodOxygenSc;
    }

    public String getBloodOxygenSv() {
        return bloodOxygenSv;
    }

    public void setBloodOxygenSv(String bloodOxygenSv) {
        this.bloodOxygenSv = bloodOxygenSv;
    }

    public String getWeightApiName() {
        return weightApiName;
    }

    public void setWeightApiName(String weightApiName) {
        this.weightApiName = weightApiName;
    }

    public String getWeightSc() {
        return weightSc;
    }

    public void setWeightSc(String weightSc) {
        this.weightSc = weightSc;
    }

    public String getWeightSv() {
        return weightSv;
    }

    public void setWeightSv(String weightSv) {
        this.weightSv = weightSv;
    }
}
