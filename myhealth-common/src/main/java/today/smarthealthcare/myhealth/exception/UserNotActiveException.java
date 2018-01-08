package today.smarthealthcare.myhealth.exception;

public class UserNotActiveException extends MyHealthException {
	public static final String MESSAGE = "User not active!";

	public UserNotActiveException() {
		super(MESSAGE);
	}
}