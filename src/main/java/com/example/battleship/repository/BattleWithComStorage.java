package com.example.battleship.repository;

import java.util.HashMap;

public class BattleWithComStorage {
    private HashMap<Integer,int[][]> mapPlayer = new HashMap<>();
    private HashMap<Integer,int[][]> mapComp = new HashMap<>();

    public void putComp(int id, int[][] poleComp){
        mapComp.put(id,poleComp);
    }
    public int[][] getPoleComp(int id){
        return mapComp.get(id);
    }

    public void makeShoot(int id, int x, int y){
        mapComp.get(id)[x][y]=2;
    }

    public void putPlayer(int id, int[][] polePlayer){
        mapPlayer.put(id,polePlayer);
    }
    public int[][] getPolePlayer(int id){
        return mapPlayer.get(id);
    }

    public void makeShootPlayer(int id, int x, int y){
        mapPlayer.get(id)[x][y]=2;
    }

}
