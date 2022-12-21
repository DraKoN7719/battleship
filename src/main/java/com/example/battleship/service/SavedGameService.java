package com.example.battleship.service;

import com.example.battleship.model.Game;
import com.example.battleship.model.SavedGame;
import com.example.battleship.repository.BattleCache;
import com.example.battleship.repository.SavedGameRepository;
import com.example.battleship.utils.PlacementUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.example.battleship.utils.PlacementUtils.placementToArray;

@Service
public class SavedGameService {

    private final SavedGameRepository savedGameRepository;

    public SavedGameService(SavedGameRepository savedGameRepository) {
        this.savedGameRepository = savedGameRepository;
    }

    public List<SavedGame> getSavedGamesByUserId(Long userId) {
        return savedGameRepository.findAllByUserId(userId);
    }

    public SavedGame getSavedGameById(UUID savedGameId) {
        return savedGameRepository.findById(savedGameId).orElse(null);
    }

    public void loadGame(UUID gameId) {
        var game = savedGameRepository.findById(gameId).get();
        if(BattleCache.getGame(gameId) != null) {
            BattleCache.deleteGame(BattleCache.getGame(gameId));
        }
        BattleCache.putGame(new Game(game.getId(), game.getUserId(), game.getBotId(), placementToArray(game.getUserField()), placementToArray(game.getBotField()), game.getTurn(),
                                     Instant.now()));
    }

    @Transactional
    public boolean saveGame(SavedGame savedGame, boolean isOverwrite) {
        var cacheGame = BattleCache.getGame(savedGame.getId());
        savedGame.setBotField(PlacementUtils.placementToString(cacheGame.getFieldPlayer2()));
        var game = savedGameRepository.findByNameGameAndUserId(savedGame.getNameGame(), savedGame.getUserId());
        if (isOverwrite || (game.isPresent() && game.get().getId().equals(savedGame.getId()))) {
            if(game.isPresent()) {
                savedGameRepository.delete(game.get());
                savedGameRepository.flush();
            }
            savedGameRepository.save(savedGame);
        } else {
            if (game.isPresent()) {
                return true;
            }
            savedGameRepository.save(savedGame);
        }
        return false;
    }

    @Transactional
    public void deleteSavedGame(UUID savedGameId) {
        savedGameRepository.deleteById(savedGameId);
    }
}
