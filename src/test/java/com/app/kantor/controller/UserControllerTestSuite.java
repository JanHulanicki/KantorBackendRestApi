package com.app.kantor.controller;

import com.app.kantor.domain.user.User;
import com.app.kantor.domain.user.UserDto;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.mapper.UserMapper;
import com.app.kantor.repository.UserRepository;
import com.app.kantor.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("UserName");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(print());


        verify(userService, times(1)).createUser(userMapper.mapToUser(any()));
    }

    @Test
    public void testBlockUser() throws Exception {
        //Given & When & Then
        mockMvc.perform(put("/v1/user/block/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGenerateUserKey() throws Exception {

        //Given
        Object generateUserKeyParam = new Object() {
            public final Long userId = 1L;

        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(generateUserKeyParam);
        User user = Mockito.mock(User.class);
        user.setId(1L);
        UserDto userDto = Mockito.mock(UserDto.class);
        userDto.setId(1L);
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(userService.getUserById(userId).orElseThrow(UserNotFoundException::new))).thenReturn(userDto);
        // When & Then
        mockMvc.perform(put("/v1/user/key/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetUsers() throws Exception {
        //Given
        List<UserDto> usersDtoList = new ArrayList<>();
        UserDto userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setName("Name1");
        usersDtoList.add(userDto1);

        when(userMapper.mapToUserDtoList(userService.getUsers())).thenReturn(usersDtoList);

        //When & Then
        mockMvc.perform(get("/v1/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].name", is("Name1")));
    }

    @Test
    public void testGetUserById() throws Exception {
        //Given
        Object generateUserKeyParam = new Object() {
            public final Long userId = 1L;
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(generateUserKeyParam);
        User user = Mockito.mock(User.class);
        UserDto userDto = Mockito.mock(UserDto.class);

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(userService.getUserById(1L).orElseThrow(UserNotFoundException::new))).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}


