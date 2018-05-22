package br.com.douglasffilho.UserServices.utils.impl;

import br.com.douglasffilho.UserServices.dto.UserDTO;
import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.utils.EntityFactory;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@Component
public class UserFactory implements EntityFactory<User> {

	private UserDTO userDTO;

	private User createValid(UserDTO userDTO) {

		return User
				.builder()
				.username(userDTO.getName())
				.email(userDTO.getEmail())
				.password(userDTO.getPassword())
				.phone(userDTO.getPhone())
				.role(userDTO.getRole())
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.isEnabled(true)
				.accountExpirationDate(LocalDateTime.now().plusYears(1))
				.credentialsExpirationDate(LocalDateTime.now().plusMonths(3))
				.build();
	}

	private User createValidTest(UserDTO userDTO) {
		return User
				.builder()
				.username(userDTO.getName())
				.email(userDTO.getEmail())
				.password(userDTO.getPassword())
				.phone(userDTO.getPhone())
				.role(userDTO.getRole())
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
		return createValid(userDTO);
	}

	@Override
	public User createTest() {
		return createValidTest(userDTO);
	}
}
