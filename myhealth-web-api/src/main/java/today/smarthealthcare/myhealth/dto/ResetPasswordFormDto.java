package today.smarthealthcare.myhealth.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class ResetPasswordFormDto {
	@NotEmpty(message = "error.hash.empty")
	private String hash;

	@NotEmpty(message = "error.password.empty")
	private String newPassword;

	private String newPasswordConfirmation;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}
}
