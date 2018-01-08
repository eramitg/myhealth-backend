package today.smarthealthcare.myhealth.ihealth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public abstract class IHealthUserDataResponseDto extends IHealthResponseDto {
    @JsonProperty(value = "CurrentRecordCount")
    private int currentRecordCount;
    @JsonProperty(value = "NextPageUrl")
    private String nextPageUrl;
    @JsonProperty(value = "PageLength")
    private int pageLength;
    @JsonProperty(value = "PageNumber")
    private int pageNumber;
    @JsonProperty(value = "PrevPageUrl")
    private String prevPageUrl;
    @JsonProperty(value = "RecordCount")
    private int recordCount;

    public int getCurrentRecordCount() {
        return currentRecordCount;
    }

    public void setCurrentRecordCount(int currentRecordCount) {
        this.currentRecordCount = currentRecordCount;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", super.toString())
                .add("currentRecordCount", currentRecordCount)
                .add("nextPageUrl", nextPageUrl)
                .add("pageLength", pageLength)
                .add("pageNumber", pageNumber)
                .add("prevPageUrl", prevPageUrl)
                .add("recordCount", recordCount)
                .toString();
    }
}
