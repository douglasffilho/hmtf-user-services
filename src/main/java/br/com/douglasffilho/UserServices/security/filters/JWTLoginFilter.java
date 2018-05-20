package br.com.douglasffilho.UserServices.security.filters;

import br.com.douglasffilho.UserServices.security.models.AccountCredentials;
import br.com.douglasffilho.UserServices.security.services.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	protected JWTLoginFilter() {
		super(new AntPathRequestMatcher(""));
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
		tokenAuthenticationService.addAuthentication(response, authResult.getName());
	}
}
