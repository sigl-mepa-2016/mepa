package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.UserDao;
import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User user) {
        this.userDao.create(user);
    }

    @Override
    public void update(User user) {
        this.userDao.update(user);
    }

    @Override
    public void delete(ObjectId id) {
        this.userDao.delete(id);
    }

    @Override
    public User getById(ObjectId id) {
        return this.userDao.getById(id);
    }

    @Override
    public User getByNameAndPassword(User user) {
        return this.userDao.getByNameAndPassword(user);
    }
}
