package com.example.battleship.service;

import com.example.battleship.repository.BattleWithComStorage;
import com.example.battleship.utils.Cords;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShootingService {
    private final BattleWithComStorage map = new BattleWithComStorage();
    private final PlacementService placementService = new PlacementService(new Random(), null);

    public Cords getShoot(int id){
        if (map.getPolePlayer(id) == null) {map.putPlayer(id, new int[10][10]);}

        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        while(map.getPolePlayer(id)[x][y] != 0) {
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
        map.getPolePlayer(id)[x][y] = 1;
        return new Cords(id,x,y);
    }

    public int getShootResult(int id, int x, int y) {
        if (map.getPoleComp(id) == null) {map.putComp(id, placementService.placeRandom()); print(map.getPoleComp(id));}
        System.out.println("Выстрел игрока - " + id + " Результат " + (map.getPoleComp(id)[x][y] == 1) );
        //удалить строчку снизу она костыльная для тестов
        if(map.getPoleComp(id)[x][y] == -2 || map.getPoleComp(id)[x][y] == -1) {map.putComp(id, placementService.placeRandom()); print(map.getPoleComp(id));}// це костыль
        if (map.getPoleComp(id)[x][y] != 1) {
            map.getPoleComp(id)[x][y] = -2;
            return 0;
        }
        else {
            map.getPoleComp(id)[x][y] = -1;
            if(isDead(map.getPoleComp(id),x,y)) return 2;
            return 1;
        }
    }

    private boolean isDead(int[][] pole, int x , int y){
        int n = 1;
        while(inBoard(x+n,y) && pole[x+n][y] == -1){
            n++;
        }
        if(inBoard(x+n,y) && pole[x+n][y] == 1) return false;
        n = 1;

        while(inBoard(x-n,y) && pole[x-n][y] == -1){
            n++;
        }
        if(inBoard(x-n,y) && pole[x-n][y] == 1) return false;
        n = 1;

        while(inBoard(x,y+n) && pole[x][y+n] == -1){
            n++;
        }
        if(inBoard(x,y+n) && pole[x][y+n] == 1) return false;
        n = 1;

        while(inBoard(x,y-n) && pole[x][y-n] == -1){
            n++;
        }
        if(inBoard(x,y-n) && pole[x][y-n] == 1) return false;

        return  true;
    }

    public boolean inBoard(int x,int y){
        return x < 10 && x > -1 && y < 10 && y > -1;
    }

    public static void print(int[][] pole){
        for(int i =0;i<10;i++) {
            for (int j = 0; j < 10; j++)
                System.out.print(pole[i][j] + " ");
            System.out.println();
        }
    }
}
