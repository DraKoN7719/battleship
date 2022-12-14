package com.example.battleship.repository;

import java.util.HashMap;

public class BattleWithComStorage {
    private final HashMap<Integer,int[][]> mapPlayer = new HashMap<>();
    private final HashMap<Integer,int[][]> mapComp = new HashMap<>();

    public void putComp(int id, int[][] poleComp){
        mapComp.put(id,poleComp);
    }
    public int[][] getPoleComp(int id){
        return mapComp.get(id);
    }


    public void putPlayer(int id, int[][] polePlayer){
        mapPlayer.put(id,polePlayer);
    }
    public int[][] getPolePlayer(int id){
        return mapPlayer.get(id);
    }
}
