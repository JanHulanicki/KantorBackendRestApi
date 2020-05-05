package com.app.kantor.service;

import com.app.kantor.domain.user.User;
import com.app.kantor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
   UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(final long id) {
        return userRepository.findById(id);
    }

    public void createUser(final User user) {
        userRepository.save(user);
    }

    public User blockUser(final long id) {
        userRepository.getOne(id).setIsBlocked(true);
        return userRepository.save(userRepository.getOne(id));
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }
}
