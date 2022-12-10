package com.example.battleship.web;

import com.example.battleship.utils.Cords;
import com.example.battleship.model.User;
import com.example.battleship.service.AuthenticationService;
import com.example.battleship.service.ShootingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShootingController {

    private final ShootingService shootingService;

    public ShootingController(ShootingService shootingService) {
        this.shootingService = shootingService;
    }
    @PostMapping("/api/shoot")
    public ResponseEntity<Integer> authorization(@RequestBody Cords cords) {
        return ResponseEntity.ok(shootingService.getShootResult(cords.getId(),cords.getX(),cords.getY()));
    }

    @PostMapping("/api/shootComp")
    public ResponseEntity<Cords> shootComp(@RequestBody Cords cords) {
        return ResponseEntity.ok(shootingService.getShoot(cords.getId()));
    }
}