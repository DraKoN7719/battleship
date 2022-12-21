package com.example.battleship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "save_game")
public class SavedGame {
    @Id
    @Column(name = "game_id")
    private UUID id;
    @Column(name = "game_name")
    private String nameGame;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "bot_id")
    private Long botId;
    @Column(name = "user_field")
    private String userField;
    @Column(name = "bot_field")
    private String botField;
    @Column(name = "turn")
    private Long turn;

    public SavedGame(UUID id, String nameGame, Long userId, Long botId, String userField, String botField, Long turn) {
        this.id = id;
        this.nameGame = nameGame;
        this.userId = userId;
        this.botId = botId;
        this.userField = userField;
        this.botField = botField;
        this.turn = turn;
    }

    public SavedGame() {
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

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
    }

    public String getBotField() {
        return botField;
    }

    public void setBotField(String botField) {
        this.botField = botField;
    }

    public Long getTurn() {
        return turn;
    }

    public void setTurn(Long turn) {
        this.turn = turn;
    }
}
