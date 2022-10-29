package com.example.battleship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "history_game")
public class HistoryGame {
    @Id
    @Column(name = "game_id")
    private UUID id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "opponent_id")
    private long opponentId;
    @Column(name = "result")
    private long resultGame;

    public HistoryGame(UUID id, long userId, long opponentId, long resultGame) {
        this.id = id;
        this.userId = userId;
        this.opponentId = opponentId;
        this.resultGame = resultGame;
    }

    public HistoryGame() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(long opponentId) {
        this.opponentId = opponentId;
    }

    public long getResultGame() {
        return resultGame;
    }

    public void setResultGame(long resultGame) {
        this.resultGame = resultGame;
    }
}
