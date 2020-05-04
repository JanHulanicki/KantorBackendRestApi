package com.app.kantor.mapper;

import com.app.kantor.domain.user.User;
import com.app.kantor.domain.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getNick(),
                userDto.getPassword(),
                userDto.getEmailAddress(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getIsBlocked(),
                userDto.getUuidKey(),
                userDto.getExpiredDate()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getNick(),
                user.getPassword(),
                user.getEmailAddress(),
                user.getName(),
                user.getSurname(),
                user.getIsBlocked(),
                user.getUuidKey(),
                user.getExpiredDate()
        );
    }


    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(user -> new UserDto())
                .collect(Collectors.toList());

    }
}
