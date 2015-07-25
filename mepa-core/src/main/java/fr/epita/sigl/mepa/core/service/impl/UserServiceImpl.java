package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.UserDao;
import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        this.userDao.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        this.userDao.deleteUser(userId);
    }

    @Override
    public User getuserById(String userId) {
        return this.userDao.getuserById(userId);
    }

    @Override
    public User getUserbyNameAndPassword(User user) {
        return this.userDao.getUserbyNameAndPassword(user);
    }
}
