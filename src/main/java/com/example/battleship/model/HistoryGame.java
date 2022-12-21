package com.example.battleship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "history_game")
public class HistoryGame {
    @Id
    @Column(name = "game_id")
    private UUID id;
    @Column(name = "player1")
    private Long player1;
    @Column(name = "player2")
    private Long player2;
    @Column(name = "result")
    private Long result;

    @Column(name = "game_time")
    private Instant gameTime;

    public HistoryGame() {

    }

    public HistoryGame(UUID id, Long player1, Long player2, Long result, Instant gameTime) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.gameTime = gameTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getPlayer1() {
        return player1;
    }

    public void setPlayer1(Long player1) {
        this.player1 = player1;
    }

    public Long getPlayer2() {
        return player2;
    }

    public void setPlayer2(Long player2) {
        this.player2 = player2;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public Instant getGameTime() {
        return gameTime;
    }

    public void setGameTime(Instant gameTime) {
        this.gameTime = gameTime;
    }
}
