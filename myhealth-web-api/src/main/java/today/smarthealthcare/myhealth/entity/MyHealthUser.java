package today.smarthealthcare.myhealth.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MY_HEALTH_USER")
public class MyHealthUser {
	@Id
	@GeneratedValue
	private Long id;
	private String email;
	@Column(name = "PASSWORD_HASH")
	private String passwordHash;
	@Column(name = "PASSWORD_SALT")
	private String passwordSalt;
	private boolean active;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "PERSON_USER_REL",
			joinColumns = {@JoinColumn(name = "USER_ID")},
			inverseJoinColumns = {@JoinColumn(name = "PERSON_ID")})
	private List<Person> persons;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
