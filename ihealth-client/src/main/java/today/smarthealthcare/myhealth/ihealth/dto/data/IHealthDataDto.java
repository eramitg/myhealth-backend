package today.smarthealthcare.myhealth.ihealth.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.TimeZone;

public abstract class IHealthDataDto {
    @JsonProperty(value = "DataID")
    private String dataId;
    @JsonProperty("MDate")
    private Long measurementDate;
    @JsonProperty(value = "Lat")
    private int latitude;
    @JsonProperty(value = "Lon")
    private int longitude;
    @JsonProperty(value = "Note")
    private String note;
    @JsonProperty(value = "LastChangeTime")
    private Long lastChangeTime;
    @JsonProperty(value = "DataSource")
    private String dataSource;
    @JsonProperty(value = "TimeZone")
    private String timeZone;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Long getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Long measurementDate) {
        this.measurementDate = measurementDate;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Long lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dataId", dataId)
                .add("measurementDate", measurementDate)
                .add("timeZone", timeZone)
                .add("latitude", latitude)
                .add("longitude", longitude)
                .add("note", note)
                .add("lastChangeTime", lastChangeTime)
                .add("dataSource", dataSource)
                .toString();
    }
}
