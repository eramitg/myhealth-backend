package today.smarthealthcare.myhealth.configuration;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.repository.MyHealthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MyHealthUserRepository myHealthUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyHealthUser myHealthUser = myHealthUserRepository.findByEmail(username);

		if (myHealthUser != null) {
			List<GrantedAuthority> authorityList = new ArrayList<>();
			return new User(username, myHealthUser.getPasswordHash(), authorityList);
		} else {
			throw new UsernameNotFoundException(username);
		}
	}
}
