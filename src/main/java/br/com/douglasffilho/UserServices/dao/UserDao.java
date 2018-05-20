package br.com.douglasffilho.UserServices.dao;

import br.com.douglasffilho.UserServices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

}
