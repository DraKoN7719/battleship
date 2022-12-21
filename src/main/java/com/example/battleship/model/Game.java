package com.example.battleship.model;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Game {
    private UUID id;
    private Long player1;
    private Long player2;
    private int[][] fieldPlayer1;
    private int[][] fieldPlayer2;
    private Long turn;
    private Instant lastAction;

    public Game() {
    }

    public Game(UUID id, Long player1, Long player2, int[][] fieldPlayer1, int[][] fieldPlayer2, Long turn,
                Instant lastAction
    ) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.fieldPlayer1 = fieldPlayer1;
        this.fieldPlayer2 = fieldPlayer2;
        this.turn = turn;
        this.lastAction = lastAction;
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

    public Long getTurn() {
        return turn;
    }

    public void setTurn(Long turn) {
        this.turn = turn;
    }

    public Instant getLastAction() {
        return lastAction;
    }

    public void setLastAction(Instant lastAction) {
        this.lastAction = lastAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(player1, game.player1) &&
               Objects.equals(player2, game.player2) && Arrays.deepEquals(fieldPlayer1,
                                                                          game.fieldPlayer1) &&
               Arrays.deepEquals(fieldPlayer2, game.fieldPlayer2) && Objects.equals(turn, game.turn) &&
               Objects.equals(lastAction, game.lastAction);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, player1, player2, turn, lastAction);
        result = 31 * result + Arrays.deepHashCode(fieldPlayer1);
        result = 31 * result + Arrays.deepHashCode(fieldPlayer2);
        return result;
    }
}
