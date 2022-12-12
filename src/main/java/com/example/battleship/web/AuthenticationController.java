package com.example.battleship.web;

import com.example.battleship.model.User;
import com.example.battleship.model.dto.AuthenticationDto;
import com.example.battleship.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/api/authentication/{userLogin}")
    public ResponseEntity<AuthenticationDto> getUserByLogin(@PathVariable("userLogin") String login) {
        return ResponseEntity.ok(authenticationService.getUserByLogin(login));
    }

    @PutMapping("/api/registration")
    public ResponseEntity<AuthenticationDto> registration(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.registration(user));
    }

    @PostMapping("/api/authorization")
    public ResponseEntity<AuthenticationDto> authorization(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.authorization(user));
    }

}
