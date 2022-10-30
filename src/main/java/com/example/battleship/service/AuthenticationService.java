package com.example.battleship.service;

import com.example.battleship.model.User;
import com.example.battleship.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long registration(User user){
        return userRepository.save(user).getId();
    }
}
