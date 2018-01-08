package today.smarthealthcare.myhealth.ihealth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public abstract class IHealthResponseDto {
    @JsonProperty(value = "Error")
    private String error;
    @JsonProperty(value = "ErrorCode")
    private String errorCode;
    @JsonProperty(value = "ErrorDescription")
    private String errorDescription;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("error", error)
                .add("errorCode", errorCode)
                .add("errorDescription", errorDescription)
                .toString();
    }
}
