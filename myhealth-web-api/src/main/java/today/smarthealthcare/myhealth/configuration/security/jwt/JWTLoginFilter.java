package today.smarthealthcare.myhealth.configuration.security.jwt;

import today.smarthealthcare.myhealth.service.TokenAuthenticationService;
import today.smarthealthcare.myhealth.service.impl.TokenAuthenticationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	private ObjectMapper objectMapper = new ObjectMapper();

	private TokenAuthenticationService tokenAuthenticationService;

	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url, "POST"));
		setAuthenticationManager(authenticationManager);
		tokenAuthenticationService = new TokenAuthenticationServiceImpl();
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws AuthenticationException, IOException, ServletException {
		Map<String, String[]> params = httpServletRequest.getParameterMap();
		AccountCredentials credentials = new AccountCredentials();
		if (!CollectionUtils.isEmpty(params)) {
			credentials.setUsername(params.get("email")[0]);
			credentials.setPassword(params.get("password")[0]);
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String name = authResult.getName();
		tokenAuthenticationService.addAuthentication(response, name);
	}
}
