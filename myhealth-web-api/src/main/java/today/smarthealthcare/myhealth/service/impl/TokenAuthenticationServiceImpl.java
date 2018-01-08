package today.smarthealthcare.myhealth.service.impl;

import today.smarthealthcare.myhealth.configuration.security.jwt.AuthenticatedUser;
import today.smarthealthcare.myhealth.service.TokenAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import today.smarthealthcare.myhealth.configuration.security.jwt.AuthenticatedUser;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {
	private static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
	private String secret = "ThisIsASecret";
	private String headerString = "Authorization";

	@Override
	public void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		response.addHeader(headerString,JWT);
	}

	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(headerString);
		token = token.replace("Bearer ", "");
		if(token != null)
		{
			// parse the token.
			String username = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			if(username != null) // we managed to retrieve a user
			{
				return new AuthenticatedUser(username);
			}
		}
		return null;
	}
}
