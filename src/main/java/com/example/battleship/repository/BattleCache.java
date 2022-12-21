package com.example.battleship.repository;

import com.example.battleship.model.Game;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class BattleCache {
    private static final ConcurrentMap<UUID, Game> games = new ConcurrentHashMap<>();

    public static void putGame(Game game) {
        games.put(game.getId(), game);
    }

    public static Game getGame(UUID id) {
        return games.get(id);
    }

    public static void deleteGame(Game game) {
        if (game != null) {
            games.remove(game.getId(), game);
        }
    }

    public static void deleteGame(UUID gameId) {
        if (gameId != null) {
            games.remove(gameId);
        }
    }

    public static List<Game> clearOldGames() {
        var deleted = games.values()
                           .stream()
                           .filter(game -> Instant.now().toEpochMilli() - game.getLastAction().toEpochMilli() > 125_000)
                           .toList();
        games.values().removeAll(deleted);
        return deleted;
    }
}
