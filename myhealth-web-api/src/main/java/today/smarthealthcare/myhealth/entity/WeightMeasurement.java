package today.smarthealthcare.myhealth.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WEIGHT_MEASUREMENT")
public class WeightMeasurement {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "DATE_TAKEN")
	private Date dateTaken;
	private Double value;
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
