package br.com.douglasffilho.UserServices.services.impl;

import br.com.douglasffilho.UserServices.dao.UserDao;
import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.services.UserService;
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
    public User save(User user) throws ServiceException {
        try {
            return userDao.saveAndFlush(user);
        } catch (NullPointerException npe) {
            throw new ServiceException("Erro ao tentar salvar usuário.");
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<User> list() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (NullPointerException npe) {
            throw new ServiceException("Nenhum usuário encontrado.");
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public User findByUsername(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (NullPointerException npe) {
            throw new ServiceException("Usuário não encontrado.");
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}
