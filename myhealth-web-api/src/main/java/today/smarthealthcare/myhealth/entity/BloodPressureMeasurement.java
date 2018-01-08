package today.smarthealthcare.myhealth.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BP_MEASUREMENT")
public class BloodPressureMeasurement {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "DATE_TAKEN")
	private Date dateTaken;
	private Double systolic;
	private Double diastolic;
	private String comments;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
