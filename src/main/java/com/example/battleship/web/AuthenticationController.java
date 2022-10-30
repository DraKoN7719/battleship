package com.example.battleship.web;

import com.example.battleship.model.User;
import com.example.battleship.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PutMapping("/api/registration")
    public ResponseEntity<Long> registration(
        @RequestBody User user
        ){
        return ResponseEntity.ok(authenticationService.registration(user));
    }

}
