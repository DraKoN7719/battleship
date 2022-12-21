package com.example.battleship.model.dto;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class GameOnlineDTO {

    private UUID id;

    private Long player1;

    private Long player2;

    private Long resultGame;

    private int[][] fieldPlayer1;

    private int[][] fieldPlayer2;

    private String status;

    private int x;

    private int y;

    private Instant lastAction;
    private Long lastActionPlayer;


    public GameOnlineDTO() {
    }

    public GameOnlineDTO(UUID id, Long player1, Long player2, Long resultGame, int[][] fieldPlayer1,
                         int[][] fieldPlayer2,
                         String status, int x, int y, Instant lastAction, Long lastActionPlayer
    ) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.resultGame = resultGame;
        this.fieldPlayer1 = fieldPlayer1;
        this.fieldPlayer2 = fieldPlayer2;
        this.status = status;
        this.x = x;
        this.y = y;
        this.lastAction = lastAction;
        this.lastActionPlayer = lastActionPlayer;
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

    public Long getResultGame() {
        return resultGame;
    }

    public void setResultGame(Long resultGame) {
        this.resultGame = resultGame;
    }

    public int[][] getFieldPlayer1() {
        return fieldPlayer1;
    }

    public void setFieldPlayer1(int[][] fieldPlayer1) {
        this.fieldPlayer1 = fieldPlayer1;
    }

    public int[][] getFieldPlayer2() {
        return fieldPlayer2;
    }

    public void setFieldPlayer2(int[][] fieldPlayer2) {
        this.fieldPlayer2 = fieldPlayer2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Instant getLastAction() {
        return lastAction;
    }

    public void setLastAction(Instant lastAction) {
        this.lastAction = lastAction;
    }

    public Long getLastActionPlayer() {
        return lastActionPlayer;
    }

    public void setLastActionPlayer(Long lastActionPlayer) {
        this.lastActionPlayer = lastActionPlayer;
    }

    @Override
    public String toString() {
        return "GameOnlineDTO{" +
               "id=" + id +
               ", player1=" + player1 +
               ", player2=" + player2 +
               ", resultGame=" + resultGame +
               ", fieldPlayer1=" + Arrays.toString(fieldPlayer1) +
               ", fieldPlayer2=" + Arrays.toString(fieldPlayer2) +
               ", status='" + status + '\'' +
               ", x=" + x +
               ", y=" + y +
               ", lastAction=" + lastAction +
               ", lastActionPlayer=" + lastActionPlayer +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameOnlineDTO that = (GameOnlineDTO) o;
        return x == that.x && y == that.y && Objects.equals(id, that.id) && Objects.equals(player1,
                                                                                           that.player1) &&
               Objects.equals(player2, that.player2) && Objects.equals(resultGame, that.resultGame) &&
               Arrays.deepEquals(fieldPlayer1, that.fieldPlayer1) && Arrays.deepEquals(fieldPlayer2,
                                                                                       that.fieldPlayer2) &&
               Objects.equals(status, that.status) && Objects.equals(lastAction, that.lastAction) &&
               Objects.equals(lastActionPlayer, that.lastActionPlayer);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, player1, player2, resultGame, status, x, y, lastAction, lastActionPlayer);
        result = 31 * result + Arrays.deepHashCode(fieldPlayer1);
        result = 31 * result + Arrays.deepHashCode(fieldPlayer2);
        return result;
    }
}
