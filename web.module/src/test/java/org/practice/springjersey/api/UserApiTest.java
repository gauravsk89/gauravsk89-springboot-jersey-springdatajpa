package org.practice.springjersey.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.practice.springjersey.domain.User;
import org.practice.springjersey.rest.UserApi;
import org.practice.springjersey.service.UserService;
import org.practice.springjersey.util.ApiLinkBuilder;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
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
public class UserApiTest {

    @InjectMocks
    private UserApi userApi;

    @Mock
    private UserService userService;

    @Mock
    private ApiLinkBuilder apiLinkBuilder;

    @Mock
    @Context
    private UriInfo uriInfo;

    private User existingUser;
    private User userToBeUpdated;

    @Before
    public void setUp(){
        existingUser = getUser(TEST_USER_ID, TEST_FIRST_NAME, TEST_LAST_NAME);
        userToBeUpdated = getUser(TEST_USER_ID, TEST_FIRST_NAME+" Updated", TEST_LAST_NAME);
    }

    @Test
    public void testCreateUser_WithNotNullUser_ShouldReturn201(){

        User user = getTestUser();
        Mockito.when(userService.createUser(user)).thenReturn(existingUser);

        when(apiLinkBuilder.createObjectLink(any(UriInfo.class), any(String.class)))
                .thenReturn(URI.create(""));

        Response response = userApi.createUser(uriInfo, user);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(user.getFirstName(), existingUser.getFirstName());
        assertEquals(user.getLastName(), existingUser.getLastName());

    }

    @Test
    public void testGetAllUsers_WithUsersAvailableInDB_ShouldReturnUserList(){

        // returns 2 users in list
        List<User> userList = getUserList();
        Mockito.when(userService.findAllUsers()).thenReturn(userList);

        Response response = userApi.getAllUsers();

        List<User> userListReturned = (List<User>)response.getEntity();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getEntity());
        assertEquals(2, userListReturned.size());
    }

    @Test
    public void testGetUser_withValidId_ShouldReturnUser(){

        when(userService.findUserById(TEST_USER_ID.toString())).thenReturn(existingUser);

        Response response = userApi.getUser(TEST_USER_ID.toString());

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getEntity());
        assertEquals(TEST_USER_ID, ((User)response.getEntity()).getId());
    }

    @Test
    public void testUpdateUser_WithValidData_ShouldUpdateItSuccessfully(){

        Response response = userApi.updateUser(TEST_USER_ID, userToBeUpdated);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    public void testDeleteUser_WithValidId_ShouldDeleteIsSuccessfully(){

        Response response = userApi.deleteUser(TEST_USER_ID);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        verify(userService, times(1)).deleteUser(TEST_USER_ID);
    }

    @After
    public void cleanUp(){
        existingUser = null;
    }
}
