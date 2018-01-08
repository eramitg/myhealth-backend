package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.MyHealthUserActivationToken;
import com.sparkpost.exception.SparkPostException;

public interface MyHealthUserActivationTokenService {
    MyHealthUserActivationToken findByToken(String token);
    boolean isTokenValid(MyHealthUserActivationToken token);
    MyHealthUserActivationToken generateToken(String email);
    void save(MyHealthUserActivationToken myHealthUserActivationToken);

	void sendActivationTokenByEmail(MyHealthUserActivationToken myHealthUserActivationToken) throws SparkPostException;
}
