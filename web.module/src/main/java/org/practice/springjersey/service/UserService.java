package org.practice.springjersey.service;

import org.practice.springjersey.domain.User;
import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> findAllUsers();

    User findUserById(String userId);

    void updateUser(Long userId, User user);

    void deleteUser(Long userId);
}
