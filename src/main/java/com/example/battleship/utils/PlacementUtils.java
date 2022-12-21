package com.example.battleship.utils;

public class PlacementUtils {

    public static int[][] maskField(int[][] field) {
        int[][] maskedField = new int[field.length][field.length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) {
                    maskedField[i][j] = 0;
                } else {
                    maskedField[i][j] = field[i][j];
                }
            }
        }
        return maskedField;
    }

    public static int[][] maskUserField(int[][] field) {
        int[][] maskedField = new int[field.length][field.length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] % 2 == 0) {
                    maskedField[i][j] = 0;
                } else {
                    maskedField[i][j] = field[i][j];
                }
            }
        }
        return maskedField;
    }

    public static int[][] placementToArray(String placement) {
        if (placement == null) {
            return null;
        }
        int[][] placementArray = new int[10][10];
        for (int i = 0, k = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++, k++) {
                if (placement.charAt(k) == '-') {
                    String symbol = String.valueOf(placement.charAt(k));
                    symbol += placement.charAt(++k);
                    placementArray[i][j] = Integer.parseInt(symbol);
                } else {
                    placementArray[i][j] = Character.getNumericValue(placement.charAt(k));
                }
            }
        }
        return placementArray;
    }

    public static String placementToString(int[][] placement) {
        if (placement == null) {
            return null;
        }
        StringBuilder placementString = new StringBuilder();
        for (int[] ints : placement) {
            for (int anInt : ints) {
                placementString.append(anInt);
            }
        }
        return placementString.toString();
    }

    public static boolean isRightPlaceShip(int[][] placement, int shipSize, int y, int x, boolean isHorizontal) {
        //Влезает ли корабль в границы
        if (isHorizontal) {
            if (x + shipSize > 10) return false;
        } else if (y + shipSize > 10) {
            return false;
        }
        //На основе положения корабля определяем как обходить матрицу
        int numberOfRoundsByY = isHorizontal ? 3 : shipSize + 2, numberOfRoundsByX = isHorizontal ? shipSize + 2 : 3;
        int startY = y - 1, startX = x - 1;

        if (y == 0) {
            startY = y;
            numberOfRoundsByY--;
        } else if (y == 9) {
            numberOfRoundsByY--;
        }

        if (x == 0) {
            startX = x;
            numberOfRoundsByX--;
        } else if (x == 9) {
            numberOfRoundsByX--;
        }

        for (int i = startY, counterY = 0; i < placement.length && counterY < numberOfRoundsByY; i++, counterY++) {
            for (int j = startX, counterX = 0;
                 j < placement[i].length && counterX < numberOfRoundsByX; j++, counterX++) {
                //Проверка внешних границ
                if (!(placement[i][j] % 2 == 0)) {
                    return false;
                }
                //Проверяем, что место для корабля свободно
                if (isHorizontal) {
                    if (i == y && j >= x && j <= x + shipSize - 1 && placement[i][j] != 0) {
                        return false;
                    }
                } else {
                    if (j == x && i >= y && i <= y + shipSize - 1 && placement[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean putDoubleDeckShip(int[][] placement) {
        for (int y = 0; y < placement.length - 1; y++) {
            for (int x = 5; x < placement[y].length - 1; x++) {
                int down = placement[y + 1][x];
                int right = placement[y][x + 1];
                if (placement[y][x] == 0 && (down == 0 || right == 0)) {
                    putShip(placement, 2, y, x, isRightPlaceShip(placement, 2, y, x, true));
                    return true;
                }
            }
        }
        for (int y = 0; y < placement.length - 1; y++) {
            if (placement[y][placement[y].length - 1] == 0 && placement[y + 1][placement[y].length - 1] == 0) {
                putShip(placement, 2, y, placement[y].length - 1, false);
                return true;
            }
        }
        for (int x = 0; x < placement[0].length - 1; x++) {
            if (placement[placement.length - 1][x] == 0 && placement[placement.length - 1][x + 1] == 0) {
                putShip(placement, 2, x, placement[x].length - 1, true);
                return true;
            }
        }
        return false;
    }

    public static void putShip(int[][] placement, int shipSize, int y, int x, boolean isHorizontal) {
        //На основе положения корабля определяем как обходить матрицу
        int numberOfRoundsByY = isHorizontal ? 3 : shipSize + 2, numberOfRoundsByX = isHorizontal ? shipSize + 2 : 3;
        int startY = y - 1, startX = x - 1;

        if (y == 0) {
            startY = y;
            numberOfRoundsByY--;
        } else if (y == 9) {
            numberOfRoundsByY--;
        }

        if (x == 0) {
            startX = x;
            numberOfRoundsByX--;
        } else if (x == 9) {
            numberOfRoundsByX--;
        }

        for (int i = startY, counterY = 0; i < placement.length && counterY < numberOfRoundsByY; i++, counterY++) {
            for (int j = startX, counterX = 0;
                 j < placement[i].length && counterX < numberOfRoundsByX; j++, counterX++) {
                //Ставим корабль и заполняем границы
                if (isHorizontal) {
                    placement[i][j] += (i == y && j >= x && j <= x + shipSize - 1) ? 1 : 2;
                } else {
                    placement[i][j] += (j == x && i >= y && i <= y + shipSize - 1) ? 1 : 2;
                }
            }
        }
    }
}
