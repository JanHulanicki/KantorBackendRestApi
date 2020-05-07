package com.app.kantor.service;

import com.app.kantor.domain.user.User;
import com.app.kantor.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestSuite {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    private User user;
    @Test
    public void createUserTest() {
        //Given
        User user = new User("nick", "password", "sample@mail.com",
                "Arnold", "Big", false, null, null);
        userRepository.save(user);

        //When
        Optional<User> userById = userRepository.findById(user.getId());

        //Then
        Assert.assertTrue(userById.isPresent());

        //CleanUp
        userRepository.deleteById(userById.get().getId());
    }
    @Test
    public void getUsersTest(){
        //Given
        user =new User("nick", "password", "sample@mail.com",
                "Name", "Surname", false, null, null);
        List<User> usersList = new ArrayList<>();
                userRepository.save(user);
        //When
        usersList = userService.getUsers();
        //Then
        assertEquals(1, usersList.size());
    }

}
