package com.example.battleship.model.dto;

import java.util.UUID;

public class SavedGameDto {
    private UUID id;
    private String gameName;
    private Long userId;
    private Long botId;
    private int[][] userField;
    private int[][] botField;
    private Long turn;

    public SavedGameDto(UUID id, String gameName, Long userId, Long botId, int[][] userField, int[][] botField, Long turn) {
        this.id = id;
        this.gameName = gameName;
        this.userId = userId;
        this.botId = botId;
        this.userField = userField;
        this.botField = botField;
        this.turn = turn;
    }

    public SavedGameDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String nameGame) {
        this.gameName = nameGame;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBotId() {
        return botId;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }

    public int[][] getUserField() {
        return userField;
    }

    public void setUserField(int[][] userField) {
        this.userField = userField;
    }

    public int[][] getBotField() {
        return botField;
    }

    public void setBotField(int[][] botField) {
        this.botField = botField;
    }

    public Long getTurn() {
        return turn;
    }

    public void setTurn(Long turn) {
        this.turn = turn;
    }
}
