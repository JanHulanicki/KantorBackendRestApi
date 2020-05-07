package com.app.kantor.domain.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String nick;
    private String password;
    private String emailAddress;
    private String name;
    private String surname;
    private Boolean isBlocked;
    private String uuidKey;
    private LocalDateTime expiredDate;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", isBlocked=" + isBlocked +
                ", uuidKey='" + uuidKey + '\'' +
                ", expiredDate=" + expiredDate +
                '}';
    }
}