package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.User;
import org.bson.types.ObjectId;

public interface UserService {
    void create(User user);

    void update(User user);

    void delete(ObjectId id);

    User getById(ObjectId id);

    User getByNameAndPassword(User user);
}
