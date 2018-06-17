package br.com.douglasffilho.UserServices.services;

import br.com.douglasffilho.UserServices.entities.User;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface UserService {
	public User createDefaultUser() throws ServiceException;

	public User save(User user) throws ServiceException;

	public List<User> list() throws ServiceException;

	public User findByUsername(String username) throws ServiceException;

	public User findByEmail(String email) throws ServiceException;

}
