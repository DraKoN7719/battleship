package com.example.battleship.service;

import com.example.battleship.model.User;
import com.example.battleship.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long registration(User user) {
        user.setPassword(encryption(user.getPassword()));
        return userRepository.save(user).getId();
    }

    public boolean authorization(User user) {
        var user1 = userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        return user1.isPresent();
    }

    public String encryption(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

}
