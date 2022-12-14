package com.example.battleship.web;

import com.example.battleship.model.Bot;
import com.example.battleship.utils.Cords;
import com.example.battleship.utils.idStrat;
import com.example.battleship.model.User;
import com.example.battleship.service.AuthenticationService;
import com.example.battleship.service.ShootingService;
import com.example.battleship.utils.PoleAndId;
import org.apache.catalina.connector.Response;
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
    public ResponseEntity<Cords> shootComp(@RequestBody idStrat cords) {
        Cords cord = null;
        if(cords.getStrat() == 0)
        cord = shootingService.getShootRandom(cords.getId());
        if(cords.getStrat() == 1)
            cord = shootingService.getShootShah(cords.getId());
        if(cords.getStrat() == 2)
            cord = shootingService.getShootDiaf(cords.getId());
        return ResponseEntity.ok(cord);
    }

    @PostMapping("/api/setPolePlayer")
    public ResponseEntity<Boolean> setPolePlayer(@RequestBody PoleAndId data) {
        shootingService.setPolePlayer(data.getPolePlayer(),data.getId());
        return ResponseEntity.ok(true);
    }
}