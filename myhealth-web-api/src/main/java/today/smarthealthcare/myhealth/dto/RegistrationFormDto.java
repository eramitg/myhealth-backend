package today.smarthealthcare.myhealth.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationFormDto {
	@NotEmpty(message = "Email should not be empty")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
