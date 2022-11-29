package com.example.battleship;

import com.example.battleship.model.User;
import com.example.battleship.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BattleshipApplicationTests {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    void contextLoads() {
        var user = new User();
        //user.setId(1L);
        user.setLogin("keklol1");
        user.setPassword("Password");
        authenticationService.registration(user);

    }

}
