package com.app.kantor.repository;

import com.app.kantor.domain.stock.Stock;
import com.app.kantor.domain.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
public class UserRepositoryTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() {
        //Given
        User user = new User("nick","password","sample@mail.com",
                "Name","Big",false,null, null);
        //When
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        //Then
        Assert.assertEquals(1, users.size());
        //CleanUp
        userRepository.deleteById(user.getId());
        Assert.assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void findById() {
        //Given
        User user = new User("nick","password","sample@mail.com",
                "Name","Big",false,null, null);
        //When
        userRepository.save(user);
        Optional<User> userById = userRepository.findById(user.getId());
        //Then
        Assert.assertTrue(userById.isPresent());
        //CleanUp
        userRepository.deleteById(user.getId());
        Assert.assertFalse(userRepository.findById(user.getId()).isPresent());
    }
}