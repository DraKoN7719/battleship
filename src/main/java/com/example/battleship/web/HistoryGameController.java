package com.example.battleship.web;

import com.example.battleship.model.dto.HistoryGameDto;
import com.example.battleship.service.HistoryGameService;
import com.example.battleship.utils.Converters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryGameController {

    private final HistoryGameService historyGameService;

    public HistoryGameController(HistoryGameService historyGameService) {
        this.historyGameService = historyGameService;
    }

    @GetMapping("/api/history-game/{userId}")
    public ResponseEntity<List<HistoryGameDto>> getGamesByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(historyGameService.getGamesByUser(userId));
    }

    @PostMapping("/api/history-game/save")
    public ResponseEntity<Void> saveHistoryGame(@RequestBody HistoryGameDto historyGameDto) {
        historyGameService.saveGame(Converters.convertToHistoryGame(historyGameDto));
        return ResponseEntity.ok().build();
    }
}
