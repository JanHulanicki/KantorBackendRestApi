package com.app.kantor.controller;

import com.app.kantor.domain.user.UserDto;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.mapper.CartCryptoMapper;
import com.app.kantor.mapper.CartNbpMapper;
import com.app.kantor.mapper.CartStockMapper;
import com.app.kantor.mapper.UserMapper;
import com.app.kantor.repository.UserRepository;
import com.app.kantor.service.CartCryptoService;
import com.app.kantor.service.CartNbpService;
import com.app.kantor.service.CartStockService;
import com.app.kantor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    CartCryptoService cartCryptoService;
    @Autowired
    CartCryptoMapper cartCryptoMapper;
    @Autowired
    CartStockService cartStockService;
    @Autowired
    CartStockMapper cartStockMapper;
    @Autowired
    CartNbpService cartNbpService;
    @Autowired
    CartNbpMapper cartNbpMapper;

    @GetMapping(value = "/user")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getUsers());
    }

    @GetMapping(value = "/user/{usrtId}")
    public UserDto getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(userId).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping(value = "/user")
    public void createUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        userService.createUser(userMapper.mapToUser(userDto));
        LOGGER.info("User has been created");
    }

    @PutMapping(value = "/user/block/{userId}")
    public UserDto blockUser(@PathVariable Long userId) {
        LOGGER.info("User has benn blocked");
        return userMapper.mapToUserDto(userService.blockUser(userId));
    }

    @PutMapping(value = "/user/key/{userId}")
    public void generateUsersKey(@PathVariable Long userId) throws UserNotFoundException {
        LocalDateTime loginTimeStart = LocalDateTime.now();
        LocalDateTime expiredDate = LocalDateTime.now().plusHours(1);
        DateTimeFormatter formattedLoginTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String key = UUID.randomUUID().toString();
        UserDto userDto = userMapper.mapToUserDto(userService.getUserById(userId).orElseThrow(UserNotFoundException::new));
        LOGGER.info("User " + userDto.getNick() + " has just logged in. " + loginTimeStart.format(formattedLoginTime));
        LOGGER.info("User's key: " + key);
        userDto.setExpiredDate(expiredDate);
        userDto.setUuidKey(key);
        userService.saveUser(userMapper.mapToUser(userDto));
    }
}
