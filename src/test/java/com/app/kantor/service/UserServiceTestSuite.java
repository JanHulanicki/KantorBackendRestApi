package com.app.kantor.service;

import com.app.kantor.domain.user.User;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class UserServiceTestSuite {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    private User user;

    @Test
    public void getUsersTest() {
        //Given
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Surname", false, null, null);
        List<User> usersList = new ArrayList<>();
        userRepository.save(user);

        //When
        usersList = userService.getUsers();

        //Then
        assertEquals(1, usersList.size());
    }

    @Test
    public void getUserByIdTest() {
        //Given
        user = new User("nick", "password", "sample@mail.com",
                "Name", "Surname", false, null, null);
        userRepository.save(user);
        Optional<User> user1 = Optional.of(new User());

        //When
        user1 = userService.getUserById(user.getId());

        //Then
        assertEquals("Name", user1.get().getName());
    }

    @Test
    public void createUserTest() throws UserNotFoundException {
        //Given
        User user = new User("nick", "password", "sample@mail.com",
                "Name", "Surname", false, null, null);

        //When
        userService.createUser(user);
        Optional<User> userById = userRepository.findById(user.getId());

        //Then
        Assert.assertTrue(userById.isPresent());
    }

    @Test
    public void saveUserTest() {
        //Given
        User user = new User("nick", "password", "sample@mail.com",
                "Name", "Surname", false, null, null);

        //When
        userService.saveUser(user);
        Optional<User> userById = userRepository.findById(user.getId());

        //Then
        Assert.assertTrue(userById.isPresent());
    }

    @Test
    public void blockUserTest() {
        //Given
        User user = new User("nick", "password", "sample@mail.com",
                "Name", "Surname", false, null, null);
        User user1 = new User();
        userService.saveUser(user);

        //When
        Assert.assertFalse(user.getIsBlocked());
        user1 = userService.blockUser(user.getId());

        //Then
        Assert.assertTrue(user1.getIsBlocked());
    }
}
