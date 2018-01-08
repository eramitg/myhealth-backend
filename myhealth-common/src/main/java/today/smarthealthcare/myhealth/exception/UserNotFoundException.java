package today.smarthealthcare.myhealth.exception;

public class UserNotFoundException extends MyHealthException {
	public static final String MESSAGE = "User not found!";

	public UserNotFoundException() {
		super(MESSAGE);
	}
}
