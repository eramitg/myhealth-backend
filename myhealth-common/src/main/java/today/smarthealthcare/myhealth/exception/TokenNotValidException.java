package today.smarthealthcare.myhealth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TokenNotValidException extends MyHealthException{

	public TokenNotValidException() {
		super("error.token.not-valid");
	}
}
