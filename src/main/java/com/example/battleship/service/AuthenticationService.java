package com.example.battleship.service;

import com.example.battleship.model.User;
import com.example.battleship.model.dto.AuthenticationDto;
import com.example.battleship.model.dto.StatusAuth;
import com.example.battleship.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationDto getUserByLogin(String login) {
        var user = userRepository.findUserByLogin(login);
        if(user.isEmpty()) {
            return new AuthenticationDto();
        }
        return new AuthenticationDto(user.get().getId(), user.get().getLogin(), null);
    }

    @Transactional
    public AuthenticationDto registration(User user) {
        user.setPassword(encryption(user.getPassword()));
        if(userRepository.findUserByLogin(user.getLogin()).isPresent()){
            return new AuthenticationDto(null, user.getLogin(), StatusAuth.INVALID_LOGIN);
        }
        var user1 = userRepository.save(user);
        return new AuthenticationDto(user1.getId(), user1.getLogin(), StatusAuth.SUCCESS);
    }

    public AuthenticationDto authorization(User user) {
        if (isAI(user.getLogin())) {
            return new AuthenticationDto(null, null, StatusAuth.NOT_FOUND);
        }
        if (userRepository.findUserByLogin(user.getLogin()).isEmpty()) {
            return new AuthenticationDto(null, user.getLogin(), StatusAuth.NOT_FOUND);
        }
        var user1 = userRepository.findUserByLoginAndPassword(user.getLogin(), encryption(user.getPassword()));
        if (user1.isEmpty()) {
            return new AuthenticationDto(null, user.getLogin(), StatusAuth.INVALID_PASSWORD);
        } else {
            return new AuthenticationDto(user1.get().getId(), user1.get().getLogin(), StatusAuth.SUCCESS);
        }
    }

    private String encryption(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

    private boolean isAI(String login) {
        return "Лёгкий ИИ".equals(login) || "Средний ИИ".equals(login) || "Сложный ИИ".equals(login);
    }

}
