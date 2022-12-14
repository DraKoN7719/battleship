package com.example.battleship.service;

import com.example.battleship.model.SavedGame;
import com.example.battleship.repository.SavedGameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    @Transactional
    public boolean saveGame(SavedGame savedGame, boolean isOverwrite) {
        var game = savedGameRepository.findByNameGameAndUserId(savedGame.getNameGame(), savedGame.getUserId());
        if (isOverwrite) {
            if(game.isPresent()) {
                savedGameRepository.delete(game.get());
                savedGameRepository.flush();
            }
            savedGameRepository.save(savedGame);
        } else {
            if
            (game.isPresent()) {
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
