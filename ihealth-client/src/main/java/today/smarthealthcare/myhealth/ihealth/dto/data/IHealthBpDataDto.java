package today.smarthealthcare.myhealth.ihealth.dto.data;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class IHealthBpDataDto extends IHealthDataDto {
    @JsonProperty(value = "BPL")
    private int bpl;
    @JsonProperty(value = "HP")
    private double systolic;
    @JsonProperty(value = "HR")
    private double heartRate;
    @JsonProperty(value = "IsArr")
    private int isArr;
    @JsonProperty(value = "LP")
    private double diastolic;

    public int getBpl() {
        return bpl;
    }

    public void setBpl(int bpl) {
        this.bpl = bpl;
    }

    public double getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getIsArr() {
        return isArr;
    }

    public void setIsArr(int isArr) {
        this.isArr = isArr;
    }

    public double getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", super.toString())
                .add("bpl", bpl)
                .add("systolic", systolic)
                .add("heartRate", heartRate)
                .add("isArr", isArr)
                .add("diastolic", diastolic)
                .toString();
    }
}
