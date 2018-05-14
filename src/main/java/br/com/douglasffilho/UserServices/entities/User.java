package br.com.douglasffilho.UserServices.entities;

import br.com.douglasffilho.UserServices.utils.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@javax.persistence.Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="phone")
    private String phone;

    @Column(name="is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name="is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name="is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name="is_enabled")
    private boolean isEnabled;

    @Column(name="account_expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime accountExpirationDate;

    @Column(name="credentials_expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime credentialsExpirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ProfileEnum role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.role.toString()));
        return authorities;
    }

    public static User buildNewUserValidBy1Year(String username, String email, String password, String phone, ProfileEnum role) {
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

}
