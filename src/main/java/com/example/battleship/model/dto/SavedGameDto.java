package com.example.battleship.model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

public class SavedGameDto {
    private UUID id;
    private String nameGame;
    private Long userId;
    private Long botId;
    private int[][] userField;
    private int[][] botField;
    private Integer turn;

    public SavedGameDto(UUID id, String nameGame, Long userId, Long botId, int[][] userField, int[][] botField, Integer turn) {
        this.id = id;
        this.nameGame = nameGame;
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

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
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

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }
}
