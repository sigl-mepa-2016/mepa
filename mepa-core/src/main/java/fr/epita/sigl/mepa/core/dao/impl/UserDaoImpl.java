package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.UserDao;
import fr.epita.sigl.mepa.core.domain.User;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends Dao implements UserDao {

    private MongoCollection userCollection;

    public UserDaoImpl() {
        super();
        Jongo jongo = new Jongo(this.db);
        this.userCollection = jongo.getCollection("user");
    }

    @Override
    public void create(User user) {
        this.userCollection.insert(user);
    }

    @Override
    public void update(User user) {
        this.userCollection.update(user.get_id()).with(user);
    }

    @Override
    public void delete(ObjectId id) {
        this.delete(id);
    }

    @Override
    public User getById(ObjectId id) {
        return this.userCollection.findOne("{_id: #}", id).as(User.class);
    }

    @Override
    public User getByNameAndPassword(User user) {
        return this.userCollection.findOne("{name: #, password: #}", user.getName(), user.getPassword()).as(User.class);
    }
}
