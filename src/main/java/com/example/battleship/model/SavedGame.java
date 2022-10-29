package com.example.battleship.model;

import java.util.UUID;

public class SavedGame {
    private UUID id;
    private String nameGame;
    private long user_id;
    private long bot_id;
    private String user_field;
    private String bot_field;
    private int turn;

    public SavedGame(UUID id, String nameGame, long user_id, long bot_id, String user_field, String bot_field, int turn) {
        this.id = id;
        this.nameGame = nameGame;
        this.user_id = user_id;
        this.bot_id = bot_id;
        this.user_field = user_field;
        this.bot_field = bot_field;
        this.turn = turn;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getBot_id() {
        return bot_id;
    }

    public void setBot_id(long bot_id) {
        this.bot_id = bot_id;
    }

    public String getUser_field() {
        return user_field;
    }

    public void setUser_field(String user_field) {
        this.user_field = user_field;
    }

    public String getBot_field() {
        return bot_field;
    }

    public void setBot_field(String bot_field) {
        this.bot_field = bot_field;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
