package today.smarthealthcare.myhealth.ihealth.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class IHealthAccessTokenResponseDto extends IHealthResponseDto {
    @JsonProperty(value = "APIName")
    private String apiName;
    @JsonProperty(value = "AccessToken")
    private String accessToken;
    @JsonProperty(value = "Expires")
    private Long expires;
    @JsonProperty(value = "RefreshToken")
    private String refreshToken;
    @JsonProperty(value = "RefreshTokenExpires")
    private Long refreshTokenExpires;
    @JsonProperty(value = "UserID")
    private String userID;
    @JsonProperty(value = "client_para")
    private String clientPara;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getRefreshTokenExpires() {
        return refreshTokenExpires;
    }

    public void setRefreshTokenExpires(Long refreshTokenExpires) {
        this.refreshTokenExpires = refreshTokenExpires;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getClientPara() {
        return clientPara;
    }

    public void setClientPara(String clientPara) {
        this.clientPara = clientPara;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", super.toString())
                .add("apiName", apiName)
                .add("accessToken", accessToken)
                .add("expires", expires)
                .add("refreshToken", refreshToken)
                .add("refreshTokenExpires", refreshTokenExpires)
                .add("userID", userID)
                .add("clientPara", clientPara)
                .toString();
    }
}
