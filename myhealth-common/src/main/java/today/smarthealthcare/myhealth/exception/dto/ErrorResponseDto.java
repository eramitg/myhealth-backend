package today.smarthealthcare.myhealth.exception.dto;

import java.util.List;

public class ErrorResponseDto {
	private List<FieldErrorDto> fieldErrors;
	private List<ErrorDto> errors;

	public List<FieldErrorDto> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDto> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<ErrorDto> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDto> errors) {
		this.errors = errors;
	}
}
