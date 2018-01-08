package today.smarthealthcare.myhealth.exception.dto;

public class ErrorDto {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorDto(String message) {
		this.message = message;
	}
}
