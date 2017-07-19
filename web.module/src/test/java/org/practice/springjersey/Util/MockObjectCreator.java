package org.practice.springjersey.Util;

import org.practice.springjersey.domain.User;

import java.util.ArrayList;
import java.util.List;

public class MockObjectCreator {

    public final static Long TEST_USER_ID = 1L;
    public final static String TEST_FIRST_NAME = "testFirstName";
    public final static String TEST_LAST_NAME = "testLastName";

    public static User getTestUser(){

        User user = getUser(null, TEST_FIRST_NAME, TEST_LAST_NAME);

        return user;
    }

    public static User getUser(Long id, String fName, String lName){

        User user = User
                .builder()
                .id(id)
                .firstName(fName)
                .lastName(lName)
                .build();

        return user;
    }

    public static List<User> getUserList(){

        List<User> userList = new ArrayList<>(2);

        userList.add(getTestUser());
        userList.add(getTestUser());

        return userList;
    }

}
