package today.smarthealthcare.myhealth.configuration;

import today.smarthealthcare.myhealth.entity.MyHealthUser;
import today.smarthealthcare.myhealth.repository.MyHealthUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private MyHealthUserRepository myHealthUserRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		MyHealthUser myHealthUser = myHealthUserRepository.findByEmail(authentication.getName());
		myHealthUser.setPasswordHash(null);
		myHealthUser.setPasswordSalt(null);
		
		response.getWriter().append(objectMapper.writeValueAsString(myHealthUser));
		response.setStatus(HttpServletResponse.SC_OK);
	}
}