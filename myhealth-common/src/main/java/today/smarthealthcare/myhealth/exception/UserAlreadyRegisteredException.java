package today.smarthealthcare.myhealth.exception;

public class UserAlreadyRegisteredException extends MyHealthException {
	public static final String MESSAGE = "User is already registered.";

	public UserAlreadyRegisteredException() {
		super(MESSAGE);
	}
}
