package today.smarthealthcare.myhealth.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NOTE")
public class Note {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "DATE_TAKEN")
	private Date dateTaken;
	private String text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
