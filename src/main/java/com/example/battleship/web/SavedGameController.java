package com.example.battleship.web;

import com.example.battleship.model.dto.SavedGameDto;
import com.example.battleship.service.SavedGameService;
import com.example.battleship.utils.Converters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class SavedGameController {

    private final SavedGameService savedGameService;

    public SavedGameController(SavedGameService savedGameService) {
        this.savedGameService = savedGameService;
    }

    @GetMapping("/api/saved-game/{userId}")
    public ResponseEntity<List<SavedGameDto>> getSavedGames(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(Converters.convertToSavedGameDtos(savedGameService.getSavedGamesByUserId(userId)));
    }

    @PostMapping("/api/saved-game/{gameId}")
    public ResponseEntity<SavedGameDto> getSavedGame(@PathVariable("gameId") UUID gameId) {
        return ResponseEntity.ok(Converters.convertToSavedGameDto(savedGameService.getSavedGameById(gameId)));
    }

    @PostMapping("/api/saved-game")
    public ResponseEntity<Void> saveGame(@RequestBody SavedGameDto savedGameDto) {
        savedGameService.saveGame(Converters.convertToSavedGame(savedGameDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/saved-game/{gameId}")
    public ResponseEntity<Void> deletePlacement(@PathVariable("gameId") UUID gameId) {
        savedGameService.deleteSavedGame(gameId);
        return ResponseEntity.noContent().build();
    }
}
