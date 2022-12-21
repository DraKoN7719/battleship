package com.example.battleship.web;

import com.example.battleship.model.Coordinate;
import com.example.battleship.model.dto.ShotDto;
import com.example.battleship.model.Game;
import com.example.battleship.service.ShootingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ShootingController {

    private final ShootingService shootingService;

    public ShootingController(ShootingService shootingService) {
        this.shootingService = shootingService;
    }
    @PostMapping("/api/shoot")
    public ResponseEntity<Integer> getShootResult(@RequestBody ShotDto shot) {
        return ResponseEntity.ok(shootingService.getShootResult(shot));
    }

    @GetMapping("/api/shootComp/{gameId}")
    public ResponseEntity<Coordinate> shootComp(@PathVariable("gameId") UUID gameId) {
        return ResponseEntity.ok(shootingService.getShot(gameId));
    }

    @PostMapping("/api/initialize-battle")
    public ResponseEntity<Boolean> initializeBattle(@RequestBody Game game) {
        shootingService.initializeBattle(game);
        return ResponseEntity.ok(true);
    }
}