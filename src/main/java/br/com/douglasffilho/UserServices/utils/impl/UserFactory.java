package br.com.douglasffilho.UserServices.utils.impl;

import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.utils.EntityFactory;
import br.com.douglasffilho.UserServices.utils.ProfileEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserFactory implements EntityFactory<User> {

	private String username;

	private String email;

	private String password;

	private String phone;

	private ProfileEnum role;

	private User createValid(String username, String email, String password, String phone, ProfileEnum role) {
		return User
				.builder()
				.username(username)
				.email(email)
				.password(password)
				.phone(phone)
				.role(role)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.isEnabled(true)
				.accountExpirationDate(LocalDateTime.now().plusYears(1))
				.credentialsExpirationDate(LocalDateTime.now().plusMonths(3))
				.build();
	}

	@Override
	public User createValid() {
		return createValid(username, email, password, phone, role);
	}

	@Override
	public User createTest() {
		return null;
	}
}
