package today.smarthealthcare.myhealth.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class ActivationFormDto {
	@NotEmpty(message = "Password is empty")
	private String password;
	@NotEmpty(message = "Password is empty")
	private String passwordConfirmation;
	@NotEmpty(message = "Token is empty!")
	private String hash;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
