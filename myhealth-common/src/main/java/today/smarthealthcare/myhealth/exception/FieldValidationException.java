package today.smarthealthcare.myhealth.exception;

import org.springframework.validation.FieldError;

import java.util.List;

public class FieldValidationException extends RuntimeException {
	private List<FieldError> fieldErrors;

	public FieldValidationException(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
