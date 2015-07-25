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
    public void createUser(User user) {
        this.userCollection.insert(user);
    }

    @Override
    public void updateUser(User user) {
        this.userCollection.update(new ObjectId(user.get_id())).with(user);
    }

    @Override
    public void deleteUser(String userId) {
        this.userCollection.remove(new ObjectId(userId));
    }

    @Override
    public User getuserById(String userId) {

        return this.userCollection.findOne("{_id: #}", new ObjectId(userId)).as(User.class);
    }

    @Override
    public User getUserbyNameAndPassword(User user) {
        return this.userCollection.findOne("{name: #, password: #}", user.getName(), user.getPassword()).as(User.class);
    }
}
