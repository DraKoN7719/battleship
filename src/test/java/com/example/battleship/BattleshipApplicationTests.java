package com.example.battleship;

import com.example.battleship.model.User;
import com.example.battleship.repository.UserRepository;
import com.example.battleship.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
class BattleshipApplicationTests {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        var user = new User();
        //user.setId(1L);
        user.setLogin("keklol");
        user.setPassword("Password");
        userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
    }

}
