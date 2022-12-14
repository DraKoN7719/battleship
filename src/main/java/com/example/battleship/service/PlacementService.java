package com.example.battleship.service;

import com.example.battleship.model.Placement;
import com.example.battleship.repository.PlacementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.battleship.utils.PlacementUtils.isRightPlaceShip;
import static com.example.battleship.utils.PlacementUtils.putDoubleDeckShip;
import static com.example.battleship.utils.PlacementUtils.putShip;

@Service
public class PlacementService {
    private final Random random;
    private final PlacementRepository placementRepository;

    public PlacementService(Random random, PlacementRepository placementRepository) {
        this.random = random;
        this.placementRepository = placementRepository;
    }

    public List<Placement> getPlacementsByUserId(Long userId) {
        return placementRepository.findAllByUserIdOrderByPlacementName(userId);
    }

    public Placement getPlacement(Long userId, String placementName) {
        return placementRepository.findPlacementByUserIdAndPlacementName(userId, placementName).orElse(null);
    }

    @Transactional
    public boolean savePlacement(Placement placement, boolean isOverwrite) {
        var place = placementRepository.findPlacementByUserIdAndPlacementName(placement.getUserId(), placement.getPlacementName());
        if(isOverwrite) {
            if(place.isPresent()) {
                placementRepository.delete(place.get());
                placementRepository.flush();
            }
            placementRepository.saveAndFlush(placement);
        } else {
            if(place.isPresent()) {
                return true;
            }
            placementRepository.save(placement);
        }
        return false;
    }

    @Transactional
    public void deletePlacement(Long userId, String placementName) {
        placementRepository.deleteByUserIdAndPlacementName(userId, placementName);
    }

    public int[][] placeRandom() {
        int[][] placement = new int[10][10];
        List<Integer> ships = new ArrayList<>(List.of(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));

        while (!ships.isEmpty()) {
            boolean isHorizontal = random.nextBoolean();
            int y = random.nextInt(10);
            int x = random.nextInt(10);
            int shipSize = ships.get(0);
            if (isRightPlaceShip(placement, shipSize, y, x, isHorizontal)) {
                putShip(placement, shipSize, y, x, isHorizontal);
                ships.remove(0);
            }
        }
        return placement;
    }

    public int[][] placeShores() {
        int[][] placement = new int[10][10];
        List<Integer> ships = new ArrayList<>(List.of(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));

        while (!ships.isEmpty()) {
            int y, x;
            boolean isHorizontal = true;
            int ship = random.nextInt(ships.size());
            int shipSize = ships.get(ship);

            if (shipSize == 1) {
                //Однопалубные ставим в центр поля
                y = random.nextInt(2, 8);
                x = random.nextInt(2, 8);
            } else {
                //Генерируем в какой край поля ставить, где 0 - верх, 1 - право, 2 - низ, 3 - лево
                int border = random.nextInt(4);
                isHorizontal = border % 2 == 0;
                if (isHorizontal) {
                    y = border == 0 ? 0 : 9;
                    x = random.nextInt(10);
                } else {
                    y = random.nextInt(10);
                    x = border == 1 ? 9 : 0;
                }
            }

            if (isRightPlaceShip(placement, shipSize, y, x, isHorizontal)) {
                putShip(placement, shipSize, y, x, isHorizontal);
                ships.remove(ship);
            }
        }
        return placement;
    }

    public int[][] placeHalfField() {
        int[][] placement = new int[10][10];
        List<Integer> ships = new ArrayList<>(List.of(4, 3, 3, 2, 2, 2));
        List<Integer> singleShips = new ArrayList<>(List.of(1, 1, 1, 1));

        while (!ships.isEmpty()) {
            boolean isHorizontal = random.nextBoolean();
            int y = random.nextInt(0, 10);
            int x = random.nextInt(5, 10);
            int shipSize = ships.get(0);
            if (isRightPlaceShip(placement, shipSize, y, x, isHorizontal)) {
                putShip(placement, shipSize, y, x, isHorizontal);
                ships.remove(0);
            }
            //При самом плохом случае не получится поставить последний двухпалубный корабль
            if (ships.size() == 1) {
                //Пытаемся его поставить, иначе начинаем полностью заново
                if (putDoubleDeckShip(placement)) {
                    ships.remove(0);
                } else {
                    placement = new int[10][10];
                    ships = new ArrayList<>(List.of(4, 3, 3, 2, 2, 2));
                }
            }
        }
        while (!singleShips.isEmpty()) {
            boolean isHorizontal = true;
            int y = random.nextInt(10);
            int x = random.nextInt(5);
            int shipSize = singleShips.get(0);
            if (isRightPlaceShip(placement, shipSize, y, x, isHorizontal)) {
                putShip(placement, shipSize, y, x, isHorizontal);
                singleShips.remove(0);
            }
        }
        return rotateMatrix(placement, random.nextInt(1, 5));
    }

    /**
     * Поворачивает матрицу numberOfTurns раз на 90 градусов по часовой стрелке
     *
     * @param matrix        поворачиваемая матрица
     * @param numberOfTurns количество поворотов
     * @return повёрнутая на 90 градусов по часовой стрелке numberOfTurns раз матрица
     */
    private int[][] rotateMatrix(int[][] matrix, int numberOfTurns) {
        numberOfTurns %= 4;
        if (numberOfTurns < 1) {
            return matrix;
        }

        int[][] rotated = rotateMatrix(matrix);
        numberOfTurns--;

        while (numberOfTurns > 0) {
            rotated = rotateMatrix(rotated);
            numberOfTurns--;
        }

        return rotated;
    }

    /**
     * Поворачивает матрицу на 90 градусов по часовой стрелке
     *
     * @param matrix поворачиваемая матрица
     * @return повёрнутая на 90 градусов по часовой стрелке матрица
     */
    private int[][] rotateMatrix(int[][] matrix) {
        int[][] rotated = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rotated[j][rotated[j].length - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }

}
