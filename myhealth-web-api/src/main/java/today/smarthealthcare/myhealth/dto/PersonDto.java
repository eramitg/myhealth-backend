package today.smarthealthcare.myhealth.dto;

import today.smarthealthcare.myhealth.entity.Gender;

import java.util.Date;

import today.smarthealthcare.myhealth.entity.Gender;

public class PersonDto {
	private String name;
	private String surname;
	private Date dateOfBirth;
	private Gender gender;
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
