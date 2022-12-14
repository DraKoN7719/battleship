package com.example.battleship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public HistoryGame(UUID id, Long player1, Long player2, Long result) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
    }

    public HistoryGame() {
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

    public void setPlayer1(Long userId) {
        this.player1 = userId;
    }

    public Long getPlayer2() {
        return player2;
    }

    public void setPlayer2(Long opponentId) {
        this.player2 = opponentId;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
