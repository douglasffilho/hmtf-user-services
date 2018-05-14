package br.com.douglasffilho.UserServices.security.service;

import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<User> usuario = Optional.ofNullable(userService.findByNome(username));
        if (usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }
}
