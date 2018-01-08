package today.smarthealthcare.myhealth.configuration.security;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.exception.UserNotActiveException;
import today.smarthealthcare.myhealth.repository.MyHealthUserRepository;
import today.smarthealthcare.myhealth.utils.PasswordUtils;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider {
	@Autowired
	private MyHealthUserRepository myHealthUserRepository;


	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		MyHealthUser myHealthUser = myHealthUserRepository.findByEmail(userDetails.getUsername());
		if (myHealthUser != null) {
			if (!myHealthUser.isActive()) {
				throw new UserNotActiveException();
			}

			String hash = PasswordUtils.getHash(myHealthUser.getPasswordSalt(), userDetails.getPassword());
			if (!StringUtils.equals(hash, myHealthUser.getPasswordHash())) {
				throw new BadCredentialsException("Invalid password");
			}
		} else {
			throw new UsernameNotFoundException("user not found");
		}

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		MyHealthUser myHealthUser = myHealthUserRepository.findByEmail(username);

		if (myHealthUser != null) {
			String password = (String) authentication.getCredentials();
			List<GrantedAuthority> authList = new ArrayList<>();
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));

			return new User(username, password, authList);
		} else {
			throw new UsernameNotFoundException("user not found");
		}
	}
}
