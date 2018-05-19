package br.com.douglasffilho.UserServices.entities;

import br.com.douglasffilho.UserServices.utils.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@javax.persistence.Entity
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "is_account_non_expired")
	private boolean isAccountNonExpired;

	@Column(name = "is_account_non_locked")
	private boolean isAccountNonLocked;

	@Column(name = "is_credentials_non_expired")
	private boolean isCredentialsNonExpired;

	@Column(name = "is_enabled")
	private boolean isEnabled;

	@Column(name = "account_expiration_date")
	private LocalDateTime accountExpirationDate;

	@Column(name = "credentials_expiration_date")
	private LocalDateTime credentialsExpirationDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private ProfileEnum role;
}
