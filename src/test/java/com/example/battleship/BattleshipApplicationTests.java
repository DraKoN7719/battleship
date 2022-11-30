package com.example.battleship;

import com.example.battleship.model.User;
import com.example.battleship.service.AuthenticationService;
import com.example.battleship.service.PlacementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Repeatable;
import java.util.Arrays;

import static com.example.battleship.utils.PlacementUtils.placementToArray;
import static com.example.battleship.utils.PlacementUtils.placementToString;

@SpringBootTest
class BattleshipApplicationTests {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PlacementService placementService;

    @Test
    void contextLoads() {
        var user = new User();
        //user.setId(1L);
        user.setLogin("keklol1");
        user.setPassword("Password");
        authenticationService.registration(user);

    }

    @Test
    @RepeatedTest(100)
    void placeRandom(){
        for (int[] array : placementService.placeShores()) {
            System.out.println(Arrays.toString(array));
        }
    }

}
