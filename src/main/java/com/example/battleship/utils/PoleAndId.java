package com.example.battleship.utils;

public class PoleAndId {
    private int[][] polePlayer;
    private int id;

    public PoleAndId(int[][] polePlayer, int id) {
        this.polePlayer = polePlayer;
        this.id = id;
    }

    public int[][] getPolePlayer() {
        return polePlayer;
    }

    public void setPolePlayer(int[][] polePlayer) {
        this.polePlayer = polePlayer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
