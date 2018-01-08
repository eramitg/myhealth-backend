package today.smarthealthcare.myhealth.service;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.entity.PasswordResetToken;
import com.sparkpost.exception.SparkPostException;

public interface MyHealthUserService {
    MyHealthUser findByEmail(String email);
    void register(String email) throws SparkPostException;
    MyHealthUser save(MyHealthUser myHealthUser);
    MyHealthUser createMyHealthUser(String email);
    void restorePassword(MyHealthUser myHealthUser) throws SparkPostException;
    void resetPassword(MyHealthUser user, String newPassword);
}