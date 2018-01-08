package today.smarthealthcare.myhealth.service;


import today.smarthealthcare.myhealth.entity.PasswordResetToken;
import com.sparkpost.exception.SparkPostException;

public interface PasswordResetTokenService {
	PasswordResetToken findByToken(String token);
	boolean isTokenValid(PasswordResetToken token);
	PasswordResetToken generateToken(String email);
	void save(PasswordResetToken token);
	void sendPasswordResetTokenByEmail(PasswordResetToken token) throws SparkPostException;
}
