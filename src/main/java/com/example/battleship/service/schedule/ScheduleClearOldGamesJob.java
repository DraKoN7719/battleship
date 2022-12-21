package com.example.battleship.service.schedule;

import com.example.battleship.model.Game;
import com.example.battleship.model.HistoryGame;
import com.example.battleship.repository.BattleCache;
import com.example.battleship.service.HistoryGameService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ScheduleClearOldGamesJob {

    private final HistoryGameService historyGameService;

    public ScheduleClearOldGamesJob(HistoryGameService historyGameService) {
        this.historyGameService = historyGameService;
    }

    @Scheduled(cron = "${battleship.clear-timeout}")
    public void clearOldGames() {
        System.out.println("Очистка в " + Instant.now());
        BattleCache.clearOldGames().forEach(g -> historyGameService.saveGame(new HistoryGame(g.getId(),
                                                                                             g.getPlayer1(),
                                                                                             g.getPlayer2(),
                                                                                             g.getPlayer2(),
                                                                                             Instant.now())));
    }
}
