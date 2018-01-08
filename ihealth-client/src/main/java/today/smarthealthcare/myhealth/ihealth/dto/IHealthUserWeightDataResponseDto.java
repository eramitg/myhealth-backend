package today.smarthealthcare.myhealth.ihealth.dto;



import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthWeightDataDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthWeightDataDto;

public class IHealthUserWeightDataResponseDto extends IHealthUserDataResponseDto {
    @JsonProperty(value = "WeightDataList")
    private List<IHealthWeightDataDto> weightData;
    @JsonProperty(value = "WeightUnit")
    private int weightUnit;

    public List<IHealthWeightDataDto> getWeightData() {
        return weightData;
    }

    public void setWeightData(List<IHealthWeightDataDto> weightData) {
        this.weightData = weightData;
    }

    public int getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(int weightUnit) {
        this.weightUnit = weightUnit;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", super.toString())
                .add("weightData", weightData)
                .add("weightUnit", weightUnit)
                .toString();
    }
}
