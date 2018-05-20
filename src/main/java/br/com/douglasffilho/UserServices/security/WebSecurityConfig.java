package br.com.douglasffilho.UserServices.security;

import br.com.douglasffilho.UserServices.security.filters.JWTAuthenticationFilter;
import br.com.douglasffilho.UserServices.security.filters.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String PERMITED_AUTH_PATH = "/auth";

	@Autowired
	private JWTLoginFilter jwtLoginFilter;

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {

		jwtLoginFilter.setAuthenticationManager(authenticationManager());

		httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, PERMITED_AUTH_PATH).permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
