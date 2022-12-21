package com.example.battleship.service;

import com.example.battleship.model.Coordinate;
import com.example.battleship.model.dto.ShotDto;
import com.example.battleship.repository.BattleCache;
import com.example.battleship.model.Game;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Service
public class ShootingService {

    private final Random random;
    private final PlacementService placementService;

    public ShootingService(Random random, PlacementService placementService) {
        this.random = random;
        this.placementService = placementService;
    }

    public void initializeBattle(Game game) {
        switch (game.getPlayer2().intValue()) {
            case 2 -> game.setFieldPlayer2(placementService.placeHalfField());
            case 3 -> game.setFieldPlayer2(placementService.placeShores());
            default -> game.setFieldPlayer2(placementService.placeRandom());
        }
        clearField(game.getFieldPlayer1());
        clearField(game.getFieldPlayer2());
        game.setLastAction(Instant.now());
        BattleCache.putGame(game);
    }

    public void clearField(int[][] field) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (field[i][j] > 1) field[i][j] = 0;
    }

    public Coordinate getShot(UUID gameId) {
        var game = BattleCache.getGame(gameId);
        if (game.getPlayer2() == 1) {
            return getShootRandom(game.getFieldPlayer1(), game);
        } else if (game.getPlayer2() == 2) {
            return getShootShah(game.getFieldPlayer1(), game);
        } else {
            return getShootDiagonal(game.getFieldPlayer1(), game);
        }
    }

    // 0-пусто 1-корабль 2-подбитый корабль 3-убитый -1 - мимо 4-клетки куда стрелять не будет но стрелять можно
    public Coordinate getShootRandom(int[][] field, Game game) {
        //print(field);
        int x, y;
        var paddedCords = findPadded(field);
        if (paddedCords != null) {
            x = paddedCords.getX();                                                                     //добивание
            y = paddedCords.getY();
            int direction = finDirection(field, x, y);
            var shoot = shootDirection(field, x, y, direction);
            x = shoot.getX();
            y = shoot.getY();

            if (field[x][y] == 1) {
                field[x][y] = 2;//попали
                if (isDead(field, x, y)) {
                    setDead(field, x, y);
                    setArea(field, x, y);
                }
            } else {
                game.setTurn(game.getPlayer1());
                field[x][y] = -1;//не попали
            }

        } else {
            var cords = findFreeSquare(field);                                                 //рандомный выстрел
            var shoot = cords.get(random.nextInt(cords.size())); //выбрали рандом клетку
            x = shoot.getX();
            y = shoot.getY();
            if (field[x][y] == 1) {
                field[x][y] = 2;//попали
                if (isDead(field, x, y)) {
                    setDead(field, x, y);
                    setArea(field, x, y);
                }
            } else {
                game.setTurn(game.getPlayer1());
                field[x][y] = -1;//не попали
            }
        }
        return new Coordinate(x, y);
    }

    public Coordinate getShootShah(int[][] field, Game game) {
        int x, y;
        var paddedCords = findPadded(field);
        if (paddedCords != null) {
            x = paddedCords.getX();                                                                     //добивание
            y = paddedCords.getY();
            int direction = finDirection(field, x, y);
            var shoot = shootDirection(field, x, y, direction);
            x = shoot.getX();
            y = shoot.getY();

            if (field[x][y] == 1) {
                field[x][y] = 2;//попали
                if (isDead(field, x, y)) {
                    setDead(field, x, y);
                    setArea(field, x, y);
                }
            } else {
                game.setTurn(game.getPlayer1());
                field[x][y] = -1;//не попали
            }

        } else {
            var cords = findFreeSquareShah(field);                                                 //рандомный выстрел
            if (cords.size() == 0) cords = findFreeSquare(field);
            var shoot = cords.get(random.nextInt(cords.size())); //выбрали рандом клетку
            x = shoot.getX();
            y = shoot.getY();
            if (field[x][y] == 1) {
                field[x][y] = 2;//попали
                if (isDead(field, x, y)) {
                    setDead(field, x, y);
                    setArea(field, x, y);
                }
            } else {
                game.setTurn(game.getPlayer1());
                field[x][y] = -1;//не попали
            }
        }
        return new Coordinate(x, y);
    }

    public Coordinate getShootDiagonal(int[][] field, Game game) {
        int x, y;
        var paddedCords = findPadded(field);
        if (paddedCords != null) {
            x = paddedCords.getX();                                                                     //добивание
            y = paddedCords.getY();
            int direction = finDirection(field, x, y);
            var shoot = shootDirection(field, x, y, direction);
            x = shoot.getX();
            y = shoot.getY();

            if (field[x][y] == 1) {
                field[x][y] = 2;//попали
                if (isDead(field, x, y)) {
                    setDead(field, x, y);
                    setArea(field, x, y);
                }
            } else {
                game.setTurn(game.getPlayer1());
                field[x][y] = -1;//не попали
            }

        } else {
            var cords = findFreeSquareDiag(field);                                                 //рандомный выстрел
            if (cords.size() == 0) cords = findFreeSquare(field);
            var shoot = cords.get(random.nextInt(cords.size())); //выбрали рандом клетку
            x = shoot.getX();
            y = shoot.getY();
            if (field[x][y] == 1) {
                field[x][y] = 2;//попали
                if (isDead(field, x, y)) {
                    setDead(field, x, y);
                    setArea(field, x, y);
                }
            } else {
                game.setTurn(game.getPlayer1());
                field[x][y] = -1;//не попали
            }
        }

        return new Coordinate(x, y);
    }


    private Coordinate shootDirection(int[][] pole, int x, int y, int direction) {
        switch (direction) {
            case 1 -> {
                while (true) {
                    if (random.nextInt(2) == 0) {
                        int n = 1;
                        while (inBoard(x + n, y) && pole[x + n][y] == 2) {
                            n++;
                        }
                        if (inBoard(x + n, y) && (pole[x + n][y] == 0 || pole[x + n][y] == 1)) {
                            return new Coordinate(x + n, y);
                        }
                    }
                    int n = 1;
                    while (inBoard(x - n, y) && pole[x - n][y] == 2) {
                        n++;
                    }
                    if (inBoard(x - n, y) && (pole[x - n][y] == 0 || pole[x - n][y] == 1)) {
                        return new Coordinate(x - n, y);
                    }
                }
            }
            case 2 -> {
                System.out.println("Достреливаю горизонталь");
                while (true) {
                    if (random.nextInt(2) == 0) {
                        int n = 1;
                        while (inBoard(x, y + n) && pole[x][y + n] == 2) {
                            n++;
                        }
                        System.out.println("Достреливаю вправо");
                        if (inBoard(x, y + n) && (pole[x][y + n] == 0 || pole[x][y + n] == 1)) {
                            return new Coordinate(x, y + n);
                        }
                    }
                    int n = 1;
                    while (inBoard(x, y - n) && pole[x][y - n] == 2) {
                        n++;
                        System.out.println(n);
                    }
                    System.out.println("Достреливаю влево");
                    if (inBoard(x, y - n) && (pole[x][y - n] == 0 || pole[x][y - n] == 1)) {
                        return new Coordinate(x, y - n);
                    }
                }
            }
            case 3 -> {
                System.out.println("Достреливаю рандомно");
                while (true) {
                    System.out.println("попытка");
                    int rnd = random.nextInt(4);
                    if (rnd == 0 && inBoard(x + 1, y) && (pole[x + 1][y] == 0 || pole[x + 1][y] == 1)) {
                        return new Coordinate(x + 1, y);
                    }
                    if (rnd == 1 && inBoard(x - 1, y) && (pole[x - 1][y] == 0 || pole[x - 1][y] == 1)) {
                        return new Coordinate(x - 1, y);
                    }
                    if (rnd == 2 && inBoard(x, y + 1) && (pole[x][y + 1] == 0 || pole[x][y + 1] == 1)) {
                        return new Coordinate(x, y + 1);
                    }
                    if (rnd == 3 && inBoard(x, y - 1) && (pole[x][y - 1] == 0 || pole[x][y - 1] == 1)) {
                        return new Coordinate(x, y - 1);
                    }
                }
            }
        }
        return null;
    }

    //1 вверх вниз
    //2 влево вправо
    private int finDirection(int[][] pole, int x, int y) {
        boolean up = false;
        boolean left = false;
        if (inBoard(x + 1, y) && pole[x + 1][y] == 2) up = true;
        if (inBoard(x - 1, y) && pole[x - 1][y] == 2) up = true;
        if (inBoard(x, y - 1) && pole[x][y - 1] == 2) left = true;
        if (inBoard(x, y + 1) && pole[x][y + 1] == 2) left = true;
        if (up) return 1;
        if (left) return 2;
        return 3;
    }

    private void setArea(int[][] field, int x, int y) {
        setBorder(x, y, field);
        int n = 1;
        while (inBoard(x + n, y) && field[x + n][y] == 3) {
            setBorder(x + n, y, field);
            n++;
        }
        n = 1;

        while (inBoard(x - n, y) && field[x - n][y] == 3) {
            setBorder(x - n, y, field);
            n++;
        }
        n = 1;

        while (inBoard(x, y + n) && field[x][y + n] == 3) {
            setBorder(x, y + n, field);
            n++;
        }
        n = 1;

        while (inBoard(x, y - n) && field[x][y - n] == 3) {
            setBorder(x, y - n, field);
            n++;
        }
    }

    private void setBorder(int x, int y, int[][] pole) {
        if (inBoard(x + 1, y) && pole[x + 1][y] == 0) pole[x + 1][y] = -1;
        if (inBoard(x - 1, y) && pole[x - 1][y] == 0) pole[x - 1][y] = -1;
        if (inBoard(x, y - 1) && pole[x][y - 1] == 0) pole[x][y - 1] = -1;
        if (inBoard(x, y + 1) && pole[x][y + 1] == 0) pole[x][y + 1] = -1;

        if (inBoard(x + 1, y + 1) && pole[x + 1][y + 1] == 0) pole[x + 1][y + 1] = -1;
        if (inBoard(x - 1, y - 1) && pole[x - 1][y - 1] == 0) pole[x - 1][y - 1] = -1;
        if (inBoard(x + 1, y - 1) && pole[x + 1][y - 1] == 0) pole[x + 1][y - 1] = -1;
        if (inBoard(x - 1, y + 1) && pole[x - 1][y + 1] == 0) pole[x - 1][y + 1] = -1;
    }

    private void setDead(int[][] field, int x, int y) {
        field[x][y] = 3;
        int n = 1;
        while (inBoard(x + n, y) && field[x + n][y] == 2) {
            field[x + n][y] = 3;
            n++;
        }
        n = 1;
        while (inBoard(x - n, y) && field[x - n][y] == 2) {
            field[x - n][y] = 3;
            n++;
        }
        n = 1;
        while (inBoard(x, y + n) && field[x][y + n] == 2) {
            field[x][y + n] = 3;
            n++;
        }
        n = 1;
        while (inBoard(x, y - n) && field[x][y - n] == 2) {
            field[x][y - n] = 3;
            n++;
        }
    }

    @Nullable
    private Coordinate findPadded(int[][] pole) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (pole[i][j] == 2) {
                    return new Coordinate(i, j);
                }
            }
        return null;
    }

    private ArrayList<Coordinate> findFreeSquare(int[][] pole) {
        ArrayList<Coordinate> cords = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (pole[i][j] == 0 || pole[i][j] == 1) cords.add(new Coordinate(i, j));
        return cords;
    }

    private ArrayList<Coordinate> findFreeSquareShah(int[][] pole) {
        ArrayList<Coordinate> cords = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if ((pole[i][j] == 0 || pole[i][j] == 1) &&
                    ((i % 2 == 0 && j % 2 == 1) || (i % 2 != 0 && j % 2 != 1))) {
                    cords.add(new Coordinate(i, j));
                }
        return cords;
    }

    private ArrayList<Coordinate> findFreeSquareDiag(int[][] pole) {
        ArrayList<Coordinate> cords = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if ((pole[i][j] == 0 || pole[i][j] == 1) && ((i == j) || (i == 9 - j))) {
                    cords.add(new Coordinate(i, j));
                }
        return cords;
    }

    public int getShootResult(ShotDto shot) {
        var game = BattleCache.getGame(shot.getGameId());
        game.setLastAction(Instant.now());
        if (game.getFieldPlayer2() == null) {
            game.setFieldPlayer2(placementService.placeRandom());
        }
        int[][] field = game.getFieldPlayer2();
        int x = shot.getCoordinate().getX();
        int y = shot.getCoordinate().getY();

        if (field[x][y] != 1) {
            field[x][y] = -1;
            game.setTurn(game.getPlayer2());
            return 0;
        } else {
            field[x][y] = 2;
            if (isDead(field, x, y)) {
                setDead(field, x, y);
                setArea(field, x, y);
                if (isWin(field)) {
                    return 3;
                } else {
                    return 2;
                }
            }
            return 1;
        }
    }

    private boolean isWin(int[][] pole) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (pole[i][j] == 1) return false;
        return true;
    }

    private boolean isDead(int[][] pole, int x, int y) {
        int n = 1;
        while (inBoard(x + n, y) && pole[x + n][y] == 2) {
            n++;
        }
        if (inBoard(x + n, y) && (pole[x + n][y] == 1)) {

            return false;
        }
        n = 1;

        while (inBoard(x - n, y) && pole[x - n][y] == 2) {
            n++;
        }
        if (inBoard(x - n, y) && (pole[x - n][y] == 1)) {

            return false;
        }
        n = 1;

        while (inBoard(x, y + n) && pole[x][y + n] == 2) {
            n++;
        }
        if (inBoard(x, y + n) && (pole[x][y + n] == 1)) {

            return false;
        }
        n = 1;

        while (inBoard(x, y - n) && pole[x][y - n] == 2) {
            n++;
        }
        if (inBoard(x, y - n) && (pole[x][y - n] == 1)) {
            return false;
        }
        System.out.println("ОН УМЕР");
        return true;
    }

    public boolean inBoard(int x, int y) {
        return x < 10 && x > -1 && y < 10 && y > -1;
    }

    public static void print(int[][] pole) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                if (pole[i][j] != -1) {
                    System.out.print(pole[i][j] + " ");
                } else {
                    System.out.print(9 + " ");
                }
            System.out.println();
        }
    }
}
