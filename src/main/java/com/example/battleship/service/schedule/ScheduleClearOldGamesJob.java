package com.example.battleship.service.schedule;

import com.example.battleship.model.HistoryGame;
import com.example.battleship.repository.BattleCache;
import com.example.battleship.service.GameOnlineService;
import com.example.battleship.service.HistoryGameService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ScheduleClearOldGamesJob {

    private final HistoryGameService historyGameService;
    private final GameOnlineService gameOnlineService;

    public ScheduleClearOldGamesJob(HistoryGameService historyGameService, GameOnlineService gameOnlineService) {
        this.historyGameService = historyGameService;
        this.gameOnlineService = gameOnlineService;
    }

    @Scheduled(cron = "${battleship.clear-timeout}")
    public void clearOldGames() {
        BattleCache.clearOldGames().forEach(g -> historyGameService.saveGame(new HistoryGame(g.getId(),
                                                                                             g.getPlayer1(),
                                                                                             g.getPlayer2(),
                                                                                             g.getPlayer2(),
                                                                                             Instant.now())));
        gameOnlineService.deleteOldGames();
    }
}
