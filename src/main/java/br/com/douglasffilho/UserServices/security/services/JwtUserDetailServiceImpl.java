package br.com.douglasffilho.UserServices.security.services;

import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.security.utils.JwtUserFactory;
import br.com.douglasffilho.UserServices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		if(user == null)
			user = userService.findByEmail(username);
		if (user != null) {
			return JwtUserFactory.create(user);
		}

		throw new UsernameNotFoundException("Usuário não encontrado.");
	}
}
