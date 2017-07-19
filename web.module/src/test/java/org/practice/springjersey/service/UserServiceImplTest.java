package org.practice.springjersey.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.practice.springjersey.domain.User;
import org.practice.springjersey.exception.ApiServiceException;
import org.practice.springjersey.repository.UserRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.practice.springjersey.Util.MockObjectCreator.TEST_FIRST_NAME;
import static org.practice.springjersey.Util.MockObjectCreator.TEST_LAST_NAME;
import static org.practice.springjersey.Util.MockObjectCreator.TEST_USER_ID;
import static org.practice.springjersey.Util.MockObjectCreator.getTestUser;
import static org.practice.springjersey.Util.MockObjectCreator.getUser;
import static org.practice.springjersey.Util.MockObjectCreator.getUserList;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User existingUser;
    private User userToBeUpdated;

    @Before
    public void setUp(){
        existingUser = getUser(TEST_USER_ID, TEST_FIRST_NAME, TEST_LAST_NAME);
        userToBeUpdated = getUser(TEST_USER_ID, TEST_FIRST_NAME+" Updated", TEST_LAST_NAME);
    }

    @Test
    public void testCreateUser_WithValidData_ShouldReturnUserId(){

        User testUser = getTestUser();
        when(userRepository.save(testUser)).thenReturn(existingUser);

        User returnedUser = userService.createUser(testUser);

        assertNotNull(returnedUser);
        assertEquals(TEST_USER_ID, returnedUser.getId());
        assertEquals(existingUser.getFirstName(), returnedUser.getFirstName());
        assertEquals(existingUser.getLastName(), returnedUser.getLastName());
    }

    @Test
    public void testFindAllUsers_withNoUsersAvailableInDB_ShouldReturnBlankList(){

        when(userRepository.findAll()).thenReturn(null);

        List<User> userList = userService.findAllUsers();

        assertNotNull(userList);
        assertEquals(0, userList.size());
    }

    @Test
    public void testFindAllUsers_withUsersAvailableInDB_ShouldReturnListOfAvailableUsers(){

        // returns 2 users in list
        List<User> userList = getUserList();

        when(userRepository.findAll()).thenReturn(userList);

        assertNotNull(userList);
        assertEquals(2, userList.size());
    }

    @Test
    public void testFindUserById_withValidId_ShouldReturnUser(){

        when(userRepository.findOne(TEST_USER_ID)).thenReturn(existingUser);

        User user = userService.findUserById("1");

        assertNotNull(user);
        assertEquals(TEST_USER_ID, user.getId());
    }

    @Test(expected = ApiServiceException.class)
    public void testUpdateUser_WithWrongUserId_ShouldThrowException(){

        when(userRepository.findOne(TEST_USER_ID)).thenReturn(null);

        userService.updateUser(TEST_USER_ID, userToBeUpdated);
    }

    @Test
    public void testUpdateUser_WithValidUserId_ShouldUpdateUser(){

        when(userRepository.findOne(TEST_USER_ID)).thenReturn(existingUser);

        userService.updateUser(TEST_USER_ID, userToBeUpdated);

        verify(userRepository, times(1)).save(existingUser);
        assertEquals(userToBeUpdated.getFirstName(), existingUser.getFirstName());
        assertEquals(userToBeUpdated.getLastName(), existingUser.getLastName());
    }

    @Test(expected = ApiServiceException.class)
    public void testDeleteUser_WithWrongId_ShouldThrowException(){

        when(userRepository.findOne(TEST_USER_ID)).thenReturn(null);

        userService.deleteUser(TEST_USER_ID);
    }

    @Test
    public void testDeleteUser_WithValidId_ShouldDelete(){

        when(userRepository.findOne(TEST_USER_ID)).thenReturn(existingUser);
        doNothing().when(userRepository).delete(TEST_USER_ID);

        userService.deleteUser(TEST_USER_ID);

        verify(userRepository, times(1)).delete(TEST_USER_ID);
    }

    @After
    public void cleanUp(){
        existingUser = null;
    }
}
