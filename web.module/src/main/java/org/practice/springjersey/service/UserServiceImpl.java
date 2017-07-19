package org.practice.springjersey.service;

import lombok.extern.slf4j.Slf4j;
import org.practice.springjersey.domain.User;
import org.practice.springjersey.exception.ApiServiceException;
import org.practice.springjersey.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Inject
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        user = userRepository.save(user);

        log.debug("persisted user {}", user);

        return user;
    }

    @Override
    public List<User> findAllUsers() {

        List<User> userList = (List<User>) userRepository.findAll();

        if(CollectionUtils.isEmpty(userList)){
            log.debug("No users found, returning blank list");
            return new ArrayList<>(0);
        }

        log.debug("Total user count {}", userList.size());
        return userList;
    }

    @Override
    public User findUserById(String userId) {

        User user = userRepository.findOne(Long.valueOf(userId));

        log.debug("User retrieved for id {} is {}", userId, user);

        return user;
    }

    @Override
    public void updateUser(Long id, User user) {

        User existingUser = userRepository.findOne(id);

        if(existingUser==null){
            throw new ApiServiceException(HttpStatus.BAD_REQUEST.value(), "UserService", "user does not exist to be updated.");
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        userRepository.save(existingUser);

        log.debug("updated user for id {} is {}", id, existingUser);
    }

    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findOne(id);

        if(existingUser==null){
            throw new ApiServiceException(HttpStatus.BAD_REQUEST.value(), "UserService", "user does not exist to be updated.");
        }

        userRepository.delete(id);

        log.debug("successfully deleted user with id {}", id);
    }
}
