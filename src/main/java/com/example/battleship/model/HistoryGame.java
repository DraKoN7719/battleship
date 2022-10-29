package com.example.battleship.model;

import java.util.UUID;

public class HistoryGame {

    private UUID id;
    private long user_id;
    private long opponent_id;
    private long resultGame;

    public HistoryGame(UUID id, long user_id, long opponent_id, long resultGame) {
        this.id = id;
        this.user_id = user_id;
        this.opponent_id = opponent_id;
        this.resultGame = resultGame;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getOpponent_id() {
        return opponent_id;
    }

    public void setOpponent_id(long opponent_id) {
        this.opponent_id = opponent_id;
    }

    public long getResultGame() {
        return resultGame;
    }

    public void setResultGame(long resultGame) {
        this.resultGame = resultGame;
    }
}
