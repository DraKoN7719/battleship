package com.example.battleship.service;

import com.example.battleship.exception.SetOrCreateGameException;
import com.example.battleship.model.HistoryGame;
import com.example.battleship.model.dto.GameOnlineDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameOnlineService {
    private static final ConcurrentHashMap<UUID, GameOnlineDTO> lobbyCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<UUID, GameOnlineDTO> gameCache = new ConcurrentHashMap<>();
    private final HistoryGameService historyGameService;

    public GameOnlineService(HistoryGameService historyGameService) {
        this.historyGameService = historyGameService;
    }

    public List<GameOnlineDTO> getListGame() {
        return lobbyCache.values().stream().toList();
    }

    public void addHistoryGame(UUID idGame, Long idUser) {
        var game = gameCache.get(idGame);
        if (game != null) {
            historyGameService.saveGame(
                new HistoryGame(idGame, game.getPlayer1(), game.getPlayer2(), idUser, Instant.now()));
            gameCache.remove(idGame, game);
        }
    }

    public void addGameOnline(GameOnlineDTO gameOnlineDTO) {
        playerInGame(gameOnlineDTO.getPlayer1());
        var game = lobbyCache.values().stream().filter(g -> g.getPlayer1().equals(gameOnlineDTO.getPlayer1())).toList();
        if (!game.isEmpty()) {
            game.forEach(g -> lobbyCache.remove(g.getId()));
        }
        lobbyCache.putIfAbsent(gameOnlineDTO.getId(), gameOnlineDTO);
    }

    public void setGameOnline(GameOnlineDTO gameOnlineDTO) {
        playerInGame(gameOnlineDTO.getPlayer2());
        var game = lobbyCache.get(gameOnlineDTO.getId());
        if (game != null && !game.getPlayer1().equals(gameOnlineDTO.getPlayer2())) {
            var lobbies = lobbyCache.values().stream().filter(g -> g.getPlayer1().equals(gameOnlineDTO.getPlayer2()))
                                    .toList();
            if (!lobbies.isEmpty()) {
                lobbies.forEach(g -> lobbyCache.remove(g.getId()));
            }
            game.setPlayer2(gameOnlineDTO.getPlayer2());
            game.setFieldPlayer2(gameOnlineDTO.getFieldPlayer2());
            game.setStatus(gameOnlineDTO.getStatus());
            game.setLastActionPlayer(game.getPlayer1());
            game.setLastAction(Instant.now());
        } else {
            throw new SetOrCreateGameException("Нельзя присоединиться к самому себе!", gameOnlineDTO.getPlayer2());
        }
    }

    public void deleteGameOnline(UUID id) {
        var game = lobbyCache.get(id);
        gameCache.putIfAbsent(id, game);
        lobbyCache.remove(id);
    }

    public void deleteGame(UUID id) {
        lobbyCache.remove(id);
    }

    public void deleteOldGames() {
        gameCache.values()
                 .stream()
                 .filter(game -> Instant.now().toEpochMilli() - game.getLastAction().toEpochMilli() > 125_000)
                 .forEach(g -> addHistoryGame(g.getId(),
                                              Objects.equals(g.getLastActionPlayer(), g.getPlayer1()) ? g.getPlayer2() :
                                                  g.getPlayer1()));
    }

    public String userHit(GameOnlineDTO gameOnlineDTO) {
        var game = gameCache.get(gameOnlineDTO.getId());
        game.setLastAction(Instant.now());
        if (gameOnlineDTO.getPlayer1() == null) {
            switch (getShootResult(game.getFieldPlayer1(), gameOnlineDTO.getX(), gameOnlineDTO.getY())) {
                case 0:
                    game.setLastActionPlayer(game.getPlayer1());
                    return "МИМО";
                case 1:
                    game.setLastActionPlayer(game.getPlayer2());
                    return "ПОПАЛ";
                case 2:
                    game.setLastActionPlayer(game.getPlayer2());
                    return "УБИЛ";
                case 3:
                    game.setLastActionPlayer(game.getPlayer1());
                    return "ИТОГ";
            }
        } else {
            switch (getShootResult(game.getFieldPlayer2(), gameOnlineDTO.getX(), gameOnlineDTO.getY())) {
                case 0:
                    game.setLastActionPlayer(game.getPlayer2());
                    return "МИМО";
                case 1:
                    game.setLastActionPlayer(game.getPlayer1());
                    return "ПОПАЛ";
                case 2:
                    game.setLastActionPlayer(game.getPlayer1());
                    return "УБИЛ";
                case 3:
                    game.setLastActionPlayer(game.getPlayer2());
                    return "ИТОГ";
            }
        }
        return "";
    }


    public boolean inBoard(int x, int y) {
        return x < 10 && x > -1 && y < 10 && y > -1;
    }

    public int getShootResult(int[][] pole, int x, int y) {
        if (pole[x][y] != 1) {
            pole[x][y] = -2;
            return 0;
        } else {
            pole[x][y] = -1;
            if (isDead(pole, x, y)) {
                if (isWin(pole)) {
                    return 3;
                } else {
                    return 2;
                }
            }
            return 1;
        }
    }

    private boolean isWin(int[][] pole) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (pole[i][j] == 1) return false;
        return true;
    }

    private boolean isDead(int[][] pole, int x, int y) {
        int n = 1;
        while (inBoard(x + n, y) && pole[x + n][y] == -1) {
            n++;
        }
        if (inBoard(x + n, y) && pole[x + n][y] == 1) return false;
        n = 1;

        while (inBoard(x - n, y) && pole[x - n][y] == -1) {
            n++;
        }
        if (inBoard(x - n, y) && pole[x - n][y] == 1) return false;
        n = 1;

        while (inBoard(x, y + n) && pole[x][y + n] == -1) {
            n++;
        }
        if (inBoard(x, y + n) && pole[x][y + n] == 1) return false;
        n = 1;

        while (inBoard(x, y - n) && pole[x][y - n] == -1) {
            n++;
        }
        return !inBoard(x, y - n) || pole[x][y - n] != 1;
    }

    private void playerInGame(Long playerId) {
        if (gameCache.values().stream()
                     .anyMatch(g -> g.getPlayer1().equals(playerId) || g.getPlayer2().equals(playerId))
        ) {
            throw new SetOrCreateGameException("Игрок уже в бою!", playerId);
        }
    }

}
