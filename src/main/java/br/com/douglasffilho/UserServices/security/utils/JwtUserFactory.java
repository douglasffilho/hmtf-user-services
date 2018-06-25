package br.com.douglasffilho.UserServices.security.utils;

import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.security.models.JwtUser;
import br.com.douglasffilho.UserServices.utils.ProfileEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

	private JwtUserFactory() {

	}

	public static JwtUser create(User user) {
		return JwtUser
				.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.password(PasswordUtils.generateBCrypt(user.getPassword()))
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.isEnabled(true)
				.authorities(mapToGrantedAutorities(user.getRole()))
				.build();
	}

	private static List<GrantedAuthority> mapToGrantedAutorities(ProfileEnum role) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
		return grantedAuthorities;
	}

}
