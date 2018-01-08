package today.smarthealthcare.myhealth.configuration.security;

import today.smarthealthcare.myhealth.configuration.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import today.smarthealthcare.myhealth.configuration.UserAuthenticationProvider;

@Configuration
public class AuthenticationConfigurerAdapter extends GlobalAuthenticationConfigurerAdapter {
	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userAuthenticationProvider);
	}
}