package today.smarthealthcare.myhealth.ihealth.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class IHealthWeightDataDto extends IHealthDataDto {
    @JsonProperty(value = "BMI")
    private double bmi;
    @JsonProperty(value = "DCI")
    private double dci;
    @JsonProperty(value = "WeightValue")
    private double weightValue;
    @JsonProperty(value = "WaterValue")
    private double waterValue;
    @JsonProperty(value = "MuscaleValue")
    private double muscaleValue;
    @JsonProperty(value = "FatValue")
    private double fatValue;
    @JsonProperty(value = "BoneValue")
    private double boneValue;

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getDci() {
        return dci;
    }

    public void setDci(double dci) {
        this.dci = dci;
    }

    public double getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(double weightValue) {
        this.weightValue = weightValue;
    }

    public double getWaterValue() {
        return waterValue;
    }

    public void setWaterValue(double waterValue) {
        this.waterValue = waterValue;
    }

    public double getMuscaleValue() {
        return muscaleValue;
    }

    public void setMuscaleValue(double muscaleValue) {
        this.muscaleValue = muscaleValue;
    }

    public double getFatValue() {
        return fatValue;
    }

    public void setFatValue(double fatValue) {
        this.fatValue = fatValue;
    }

    public double getBoneValue() {
        return boneValue;
    }

    public void setBoneValue(double boneValue) {
        this.boneValue = boneValue;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", super.toString())
                .add("bmi", bmi)
                .add("dci", dci)
                .add("weightValue", weightValue)
                .add("waterValue", waterValue)
                .add("muscaleValue", muscaleValue)
                .add("fatValue", fatValue)
                .add("boneValue", boneValue)
                .toString();
    }
}
