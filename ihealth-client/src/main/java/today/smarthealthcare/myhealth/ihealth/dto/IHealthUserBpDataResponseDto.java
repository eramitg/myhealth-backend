package today.smarthealthcare.myhealth.ihealth.dto;



import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthBpDataDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

import today.smarthealthcare.myhealth.ihealth.dto.data.IHealthBpDataDto;

public class IHealthUserBpDataResponseDto extends IHealthUserDataResponseDto {
    @JsonProperty(value = "BPDataList")
    private List<IHealthBpDataDto> bpData;
    @JsonProperty(value = "BPUnit")
    private int bpUnit;

    public List<IHealthBpDataDto> getBpData() {
        return bpData;
    }

    public void setBpData(List<IHealthBpDataDto> bpData) {
        this.bpData = bpData;
    }

    public int getBpUnit() {
        return bpUnit;
    }

    public void setBpUnit(int bpUnit) {
        this.bpUnit = bpUnit;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", super.toString())
                .add("bpDataList", bpData)
                .add("bpUnit", bpUnit)
                .toString();
    }
}
