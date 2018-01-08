package today.smarthealthcare.myhealth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidationException extends MyHealthException{
	public ValidationException() {
		super("error.validaition");
	}
}

