package com.app.kantor.mapper;

import com.app.kantor.domain.user.User;
import com.app.kantor.domain.user.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@EnableTransactionManagement
@PersistenceContext
@AutoConfigureTestDatabase
public class UserMapperTestSuite {
   @Autowired
   UserMapper userMapper;
    @Test
    public void mapToUserTest(){
       //Given
        UserDto userDto = new UserDto();
        User user = new User();
        userDto.setName("Username");

        //When
        user = userMapper.mapToUser(userDto);

        //Then
        assertEquals("Username", user.getName());
    }

    @Test
    public void mapToUserDtoTest(){
        //Given
        UserDto userDto = new UserDto();
        User user = new User();
        user.setName("Username");

        //When
        userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals("Username", userDto.getName());
    }
}
