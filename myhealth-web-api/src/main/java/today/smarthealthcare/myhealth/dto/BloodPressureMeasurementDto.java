package today.smarthealthcare.myhealth.dto;

import java.util.Date;

public class BloodPressureMeasurementDto {
	private Long id;
	private Date date;
	private Double systolic;
	private Double diastolic;
	private String comments;
	private Long personId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getSystolic() {
		return systolic;
	}

	public void setSystolic(Double systolic) {
		this.systolic = systolic;
	}

	public Double getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(Double diastolic) {
		this.diastolic = diastolic;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
