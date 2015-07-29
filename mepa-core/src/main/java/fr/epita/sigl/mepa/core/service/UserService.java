package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.User;
import org.bson.types.ObjectId;

/**
 * Created by Calu on 28/07/2015.
 */
public interface UserService {
    void create(User user);

    void update(User user);

    void delete(ObjectId id);

    User getById(ObjectId id);

    User getByNameAndPassword(User user);
}
