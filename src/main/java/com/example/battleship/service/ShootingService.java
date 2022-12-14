package com.example.battleship.service;

import com.example.battleship.repository.BattleWithComStorage;
import com.example.battleship.utils.Cords;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class ShootingService {
    private final BattleWithComStorage map = new BattleWithComStorage();
    private final PlacementService placementService = new PlacementService(new Random(), null);

    public void setPolePlayer(int[][] pole, int id) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (pole[i][j] >= 2) pole[i][j] = 0;
        map.putPlayer(id, pole);
    }

    // 0-пусто 1-корабль 2-подбитый корабль 3-убитый -1 - мимо 4-клетки куда стрелять не будет но стрелять можно
    public Cords getShootRandom(int id) {
        Random r = new Random();
        print(map.getPolePlayer(id));
        int x, y;
        Cords paddedCords = findPadded(map.getPolePlayer(id));
        if (paddedCords != null) {
            x = paddedCords.getX();                                                                     //добивание
            y = paddedCords.getY();
            int direction = finDirection(map.getPolePlayer(id), x, y);
            Cords shoot = shootDirection(map.getPolePlayer(id), x, y, r, direction);
            x = shoot.getX();
            y = shoot.getY();

            if (map.getPolePlayer(id)[x][y] == 1) {
                map.getPolePlayer(id)[x][y] = 2;//попали
                if (isDeadComp(map.getPolePlayer(id), x, y)) {
                    setDead(id, x, y);
                    setArea(x, y, id);
                }
            } else {
                map.getPolePlayer(id)[x][y] = -1;//не попали
            }

        } else {
            ArrayList<Cords> cords = findFreeSquare(map.getPolePlayer(id));                                                 //рандомный выстрел
            Cords shoot = cords.get(r.nextInt(cords.size())); //выбрали рандом клетку
            x = shoot.getX();
            y = shoot.getY();
            if (map.getPolePlayer(id)[x][y] == 1) {
                map.getPolePlayer(id)[x][y] = 2;//попали
                if (isDeadComp(map.getPolePlayer(id), x, y)) {
                    setDead(id, x, y);
                    setArea(x, y, id);
                }
            } else {
                map.getPolePlayer(id)[x][y] = -1;//не попали
            }
        }

        return new Cords(id, x, y);
    }


    public Cords getShootShah(int id) {
        Random r = new Random();
        int x, y;
        Cords paddedCords = findPadded(map.getPolePlayer(id));
        if (paddedCords != null) {
            x = paddedCords.getX();                                                                     //добивание
            y = paddedCords.getY();
            int direction = finDirection(map.getPolePlayer(id), x, y);
            Cords shoot = shootDirection(map.getPolePlayer(id), x, y, r, direction);
            x = shoot.getX();
            y = shoot.getY();

            if (map.getPolePlayer(id)[x][y] == 1) {
                map.getPolePlayer(id)[x][y] = 2;//попали
                if (isDeadComp(map.getPolePlayer(id), x, y)) {
                    setDead(id, x, y);
                    setArea(x, y, id);
                }
            } else {
                map.getPolePlayer(id)[x][y] = -1;//не попали
            }

        } else {
            ArrayList<Cords> cords = findFreeSquareShah(map.getPolePlayer(id));                                                 //рандомный выстрел
            if (cords.size() == 0) cords = findFreeSquare(map.getPolePlayer(id));
            Cords shoot = cords.get(r.nextInt(cords.size())); //выбрали рандом клетку
            x = shoot.getX();
            y = shoot.getY();
            if (map.getPolePlayer(id)[x][y] == 1) {
                map.getPolePlayer(id)[x][y] = 2;//попали
                if (isDeadComp(map.getPolePlayer(id), x, y)) {
                    setDead(id, x, y);
                    setArea(x, y, id);
                }
            } else {
                map.getPolePlayer(id)[x][y] = -1;//не попали
            }
        }

        return new Cords(id, x, y);
    }


    public Cords getShootDiaf(int id) {
        Random r = new Random();
        int x, y;
        Cords paddedCords = findPadded(map.getPolePlayer(id));
        if (paddedCords != null) {
            x = paddedCords.getX();                                                                     //добивание
            y = paddedCords.getY();
            int direction = finDirection(map.getPolePlayer(id), x, y);
            Cords shoot = shootDirection(map.getPolePlayer(id), x, y, r, direction);
            x = shoot.getX();
            y = shoot.getY();

            if (map.getPolePlayer(id)[x][y] == 1) {
                map.getPolePlayer(id)[x][y] = 2;//попали
                if (isDeadComp(map.getPolePlayer(id), x, y)) {
                    setDead(id, x, y);
                    setArea(x, y, id);
                }
            } else {
                map.getPolePlayer(id)[x][y] = -1;//не попали
            }

        } else {
            ArrayList<Cords> cords = findFreeSquareDiag(map.getPolePlayer(id));                                                 //рандомный выстрел
            if (cords.size() == 0) cords = findFreeSquare(map.getPolePlayer(id));
            Cords shoot = cords.get(r.nextInt(cords.size())); //выбрали рандом клетку
            x = shoot.getX();
            y = shoot.getY();
            if (map.getPolePlayer(id)[x][y] == 1) {
                map.getPolePlayer(id)[x][y] = 2;//попали
                if (isDeadComp(map.getPolePlayer(id), x, y)) {
                    setDead(id, x, y);
                    setArea(x, y, id);
                }
            } else {
                map.getPolePlayer(id)[x][y] = -1;//не попали
            }
        }

        return new Cords(id, x, y);
    }


    private Cords shootDirection(int[][] pole, int x, int y, Random r, int direction) {
        switch (direction) {
            case 1: {
                while (true) {
                    if (r.nextInt(2) == 0) {
                        int n = 1;
                        while (inBoard(x + n, y) && pole[x + n][y] == 2) {
                            n++;
                        }
                        if (inBoard(x + n, y) && (pole[x + n][y] == 0 || pole[x + n][y] == 1)) {
                            return new Cords(0, x + n, y);
                        }
                    }
                    int n = 1;
                    while (inBoard(x - n, y) && pole[x - n][y] == 2) {
                        n++;
                    }
                    if (inBoard(x - n, y) && (pole[x - n][y] == 0 || pole[x - n][y] == 1))
                        return new Cords(0, x - n, y);
                }
            }
            case 2: {
                System.out.println("Достреливаю горизонталь");
                while (true) {
                    if (r.nextInt(2) == 0) {
                        int n = 1;
                        while (inBoard(x, y + n) && pole[x][y + n] == 2) {
                            n++;
                        }
                        System.out.println("Достреливаю вправо");
                        if (inBoard(x, y + n) && (pole[x][y + n] == 0 || pole[x][y + n] == 1))
                            return new Cords(0, x, y + n);
                    }
                    int n = 1;
                    while (inBoard(x, y - n) && pole[x][y - n] == 2) {
                        n++;
                        System.out.println(n);
                    }
                    System.out.println("Достреливаю влево");
                    if (inBoard(x, y - n) && (pole[x][y - n] == 0 || pole[x][y - n] == 1))
                        return new Cords(0, x, y - n);
                }
            }
            case 3: {
                System.out.println("Достреливаю рандомно");
                while (true) {
                    System.out.println("попытка");
                    int rnd = r.nextInt(4);
                    if (rnd == 0 && inBoard(x + 1, y) && (pole[x + 1][y] == 0 || pole[x + 1][y] == 1)) {
                        return new Cords(0, x + 1, y);
                    }
                    if (rnd == 1 && inBoard(x - 1, y) && (pole[x - 1][y] == 0 || pole[x - 1][y] == 1)) {
                        return new Cords(0, x - 1, y);
                    }
                    if (rnd == 2 && inBoard(x, y + 1) && (pole[x][y + 1] == 0 || pole[x][y + 1] == 1)) {
                        return new Cords(0, x, y + 1);
                    }
                    if (rnd == 3 && inBoard(x, y - 1) && (pole[x][y - 1] == 0 || pole[x][y - 1] == 1)) {
                        return new Cords(0, x, y - 1);
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

    private void setArea(int x, int y, int id) {
        int[][] pole = map.getPolePlayer(id);
        setBorder(x, y, pole);
        int n = 1;
        while (inBoard(x + n, y) && pole[x + n][y] == 3) {
            setBorder(x + n, y, pole);
            n++;
        }
        n = 1;

        while (inBoard(x - n, y) && pole[x - n][y] == 3) {
            setBorder(x - n, y, pole);
            n++;
        }
        n = 1;

        while (inBoard(x, y + n) && pole[x][y + n] == 3) {
            setBorder(x, y + n, pole);
            n++;
        }
        n = 1;

        while (inBoard(x, y - n) && pole[x][y - n] == 3) {
            setBorder(x, y - n, pole);
            n++;
        }
        map.putPlayer(id, pole);
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

    private void setDead(int id, int x, int y) {
        int[][] pole = map.getPolePlayer(id);
        pole[x][y] = 3;
        int n = 1;
        while (inBoard(x + n, y) && pole[x + n][y] == 2) {
            pole[x + n][y] = 3;
            n++;
        }
        n = 1;
        while (inBoard(x - n, y) && pole[x - n][y] == 2) {
            pole[x - n][y] = 3;
            n++;
        }
        n = 1;
        while (inBoard(x, y + n) && pole[x][y + n] == 2) {
            pole[x][y + n] = 3;
            n++;
        }
        n = 1;
        while (inBoard(x, y - n) && pole[x][y - n] == 2) {
            pole[x][y - n] = 3;
            n++;
        }
        map.putPlayer(id, pole);
    }


    @Nullable
    private Cords findPadded(int[][] pole) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (pole[i][j] == 2)
                    return new Cords(0, i, j);
            }
        return null;
    }

    private ArrayList<Cords> findFreeSquare(int[][] pole) {
        ArrayList<Cords> cords = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (pole[i][j] == 0 || pole[i][j] == 1) cords.add(new Cords(0, i, j));
        return cords;
    }

    private ArrayList<Cords> findFreeSquareShah(int[][] pole) {
        ArrayList<Cords> cords = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if ((pole[i][j] == 0 || pole[i][j] == 1) && ((i % 2 == 0 && j % 2 == 1) || (i % 2 != 0 && j % 2 != 1)))
                    cords.add(new Cords(0, i, j));
        return cords;
    }

    private ArrayList<Cords> findFreeSquareDiag(int[][] pole) {
        ArrayList<Cords> cords = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if ((pole[i][j] == 0 || pole[i][j] == 1) && ((i == j) || (i == 9-j)))
                    cords.add(new Cords(0, i, j));
        return cords;
    }

    public int getShootResult(int id, int x, int y) {
        if (map.getPoleComp(id) == null) {
            map.putComp(id, placementService.placeRandom());
            print(map.getPoleComp(id));
        }

        if (map.getPoleComp(id)[x][y] != 1) {
            map.getPoleComp(id)[x][y] = -2;
            return 0;
        } else {
            map.getPoleComp(id)[x][y] = -1;
            if (isDead(map.getPoleComp(id), x, y))
                if (isVin(map.getPoleComp(id))) return 3;
                else
                    return 2;
            return 1;
        }
    }

    private boolean isVin(int[][] pole) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (pole[i][j] == 1) return false;
        return true;
    }

    private boolean isDead(int[][] pole, int x, int y) {
        int n = 1;
        while (inBoard(x + n, y) && pole[x + n][y] == -1) {
            n++;
        }
        if (inBoard(x + n, y) && pole[x + n][y] == 1) return false;
        n = 1;

        while (inBoard(x - n, y) && pole[x - n][y] == -1) {
            n++;
        }
        if (inBoard(x - n, y) && pole[x - n][y] == 1) return false;
        n = 1;

        while (inBoard(x, y + n) && pole[x][y + n] == -1) {
            n++;
        }
        if (inBoard(x, y + n) && pole[x][y + n] == 1) return false;
        n = 1;

        while (inBoard(x, y - n) && pole[x][y - n] == -1) {
            n++;
        }
        if (inBoard(x, y - n) && pole[x][y - n] == 1) return false;
        return true;
    }

    private boolean isDeadComp(int[][] pole, int x, int y) {
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
                if (pole[i][j] != -1) System.out.print(pole[i][j] + " ");
                else System.out.print(9 + " ");
            System.out.println();
        }
    }
}
