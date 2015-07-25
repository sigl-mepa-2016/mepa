package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.User;

public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);

    User getuserById(String userId);

    User getUserbyNameAndPassword(User user);
}
