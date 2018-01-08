package today.smarthealthcare.myhealth.ihealth.dto.data;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class IHealthBoDataDto extends IHealthDataDto {
    @JsonProperty(value = "BO")
    private double bloodOxygen;
    @JsonProperty(value = "HR")
    private double heartRate;

    public double getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(int bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", super.toString())
                .add("bloodOxygen", bloodOxygen)
                .add("heartRate", heartRate)
                .toString();
    }
}
