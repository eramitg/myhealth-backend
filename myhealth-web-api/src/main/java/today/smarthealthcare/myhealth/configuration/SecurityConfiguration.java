package today.smarthealthcare.myhealth.configuration;

import today.smarthealthcare.myhealth.configuration.security.AuthenticationProviderImpl;
import today.smarthealthcare.myhealth.configuration.security.jwt.JWTAuthenticationFilter;
import today.smarthealthcare.myhealth.configuration.security.jwt.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import today.smarthealthcare.myhealth.configuration.security.AuthenticationProviderImpl;
import today.smarthealthcare.myhealth.configuration.security.jwt.JWTAuthenticationFilter;
import today.smarthealthcare.myhealth.configuration.security.jwt.JWTLoginFilter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Autowired
    private AuthenticationProviderImpl authenticationProviderImpl;

	@Autowired
	private CorsFilter corsFilter;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean(name = "myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderImpl);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/rest/registration",
                "/activation",
                "/reset-password",
                "/i18n/**",
                "/rest/activation/**",
                "/rest/activate/**",
                "/rest/reactivate",
                "/rest/password/**",
                "/rest/auth-rules",
                "/rest/reset-password/**",
                "/rest/logout",
                "/rest/email-verification/**",
                "/rest/terms-of-use/**",
                "/rest/property/privacy-statement-url/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .permitAll();

        http
                .httpBasic();

        http
                .headers().cacheControl();
        http.csrf().disable();

		http
				.httpBasic();

		http
				.headers().cacheControl();




		http.csrf().disable() // disable csrf for our requests.
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers(HttpMethod.POST,"/login").permitAll()
				.anyRequest().authenticated()
				.and()
				// We filter the api/login requests
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in header
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
