package br.com.douglasffilho.UserServices.services.impl;

import br.com.douglasffilho.UserServices.dao.UserDao;
import br.com.douglasffilho.UserServices.dto.UserDTO;
import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.services.UserService;
import br.com.douglasffilho.UserServices.utils.ProfileEnum;
import br.com.douglasffilho.UserServices.utils.impl.UserFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User createDefaultUser() throws ServiceException {
		try {
			final User user = UserFactory
					.builder()
					.userDTO(UserDTO
							.builder()
							.name(System.getenv("DEFAULT_SYSTEM_ADMIN_USERNAME"))
							.email(System.getenv("DEFAULT_SYSTEM_ADMIN_EMAIL"))
							.phone(System.getenv("DEFAULT_SYSTEM_ADMIN_PHONE"))
							.password(System.getenv("DEFAULT_SYSTEM_ADMIN_PASSWORD"))
							.role(ProfileEnum.ROLE_ADMIN)
							.build())
					.build()
					.createValid();

			return this.save(user);
		} catch (final Exception ex) {
			throw new ServiceException("Erro ao tentar criar usuário padrão.", ex);
		}
	}

	@Override
	public User save(final User user) throws ServiceException {
		try {
			return this.userDao.saveAndFlush(user);
		} catch (final NullPointerException npe) {
			throw new ServiceException("Erro ao tentar salvar usuário.");
		} catch (final Exception ex) {
			throw new ServiceException(ex.getMessage());
		}
	}

	@Override
	public List<User> list() throws ServiceException {
		try {
			return this.userDao.findAll();
		} catch (final NullPointerException npe) {
			throw new ServiceException("Nenhum usuário encontrado.");
		} catch (final Exception ex) {
			throw new ServiceException(ex.getMessage());
		}
	}

	@Override
	public User findByUsername(final String username) throws ServiceException {
		try {
			return this.userDao.findByUsername(username);
		} catch (final NullPointerException npe) {
			throw new ServiceException("Usuário não encontrado.");
		} catch (final Exception ex) {
			throw new ServiceException(ex.getMessage());
		}
	}

	@Override
	public User findByEmail(final String email) throws ServiceException {
		try {
			return this.userDao.findByEmail(email);
		} catch (final NullPointerException npe) {
			throw new ServiceException("Usuário não encontrado.");
		} catch (final Exception ex) {
			throw new ServiceException(ex.getMessage());
		}
	}

}
