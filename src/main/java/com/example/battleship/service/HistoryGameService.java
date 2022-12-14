package com.example.battleship.service;

import com.example.battleship.model.HistoryGame;
import com.example.battleship.model.dto.HistoryGameDto;
import com.example.battleship.repository.HistoryGameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HistoryGameService {
    private final HistoryGameRepository historyGameRepository;

    public HistoryGameService(HistoryGameRepository historyGameRepository) {
        this.historyGameRepository = historyGameRepository;
    }

    @Transactional
    public void saveGame(HistoryGame historyGame) {
        historyGameRepository.save(historyGame);
    }

    public HistoryGame getGame(UUID id) {
        return historyGameRepository.findById(id).orElse(null);
    }

    public List<HistoryGameDto> getGamesByUser(Long userId) {
        return historyGameRepository.findAllByUserId(userId)
                                    .stream()
                                    .map(p -> new HistoryGameDto(p.getId(), p.getPlayer1(), p.getLogin1(), p.getPlayer2(), p.getLogin2(), p.getResult()))
                                    .peek(historyGame -> {
                                        if (!historyGame.getPlayer1().equals(userId)) {
                                            long temp = historyGame.getPlayer1();
                                            historyGame.setPlayer1(userId);
                                            historyGame.setPlayer2(temp);
                                            String tempLogin = historyGame.getLogin1();
                                            historyGame.setLogin1(historyGame.getLogin2());
                                            historyGame.setLogin2(tempLogin);
                                        }
                                    }).toList();
    }
}
