package today.smarthealthcare.myhealth.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;

@Component
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		logger.debug("auth exception = {}", exception.getMessage());

		if (exception instanceof BadCredentialsException) {
			Map<String, String> map = new HashMap<>();
			map.put("messageCode", "error.bad-credentials");

			if (exception.getMessage().contains("/")) {
				List<String> splitToList = Splitter.on("/").omitEmptyStrings().splitToList(exception.getMessage());

				if (!splitToList.isEmpty()) {
					map.put("attempt", splitToList.get(0));
					map.put("maxAttempts", splitToList.get(1));
				}
			}

			response.getWriter().append(objectMapper.writeValueAsString(map));
		} else if (exception instanceof LockedException) {
			Map<String, String> map = new HashMap<>();
			map.put("messageCode", "error.account-locked");
			// "kolhoz" method of storing time until account is locked
			map.put("param", exception.getMessage());
			response.getWriter().append(objectMapper.writeValueAsString(map));
		} else if (exception instanceof DisabledException) {
			Map<String, String> map = new HashMap<>();
			map.put("messageCode", "error.account-disabled");
			response.getWriter().append(objectMapper.writeValueAsString(map));
		} else {
			response.getWriter().append(exception.getMessage());
		}

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}