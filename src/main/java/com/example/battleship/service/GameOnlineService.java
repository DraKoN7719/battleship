package com.example.battleship.service;

import com.example.battleship.model.dto.GameOnlineDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameOnlineService {
    private final List<GameOnlineDTO> listLobby = new ArrayList<>();
    private final List<GameOnlineDTO> listGame = new ArrayList<>();

    public List<GameOnlineDTO> getListGame() {
        return listLobby;
    }

    public void addGameOnline(GameOnlineDTO gameOnlineDTO) {
        if (listLobby.stream().filter(x -> x.getId().equals(gameOnlineDTO.getId())).count() == 0) {
            listLobby.add(gameOnlineDTO);
        }
    }

    public void setGameOnline(GameOnlineDTO gameOnlineDTO) {
        listLobby.stream().filter(x -> x.getId().equals(gameOnlineDTO.getId())).forEach(x -> {
            x.setPlayer2(gameOnlineDTO.getPlayer2());
            x.setFieldPlayer2(gameOnlineDTO.getFieldPlayer2());
            x.setStatus(gameOnlineDTO.getStatus());
        });
    }

    public void deleteGameOnline(UUID id) {
        listLobby.stream().filter((x) -> x.getId().equals(id)).forEach(x -> {
            if (listGame.stream().filter(y -> y.getId().equals(id)).count() == 0) {
                listGame.add(x);
            }
        });
        for (GameOnlineDTO element : listLobby) {
            if (element.getId().equals(id)) listLobby.remove(element);
            break;
        }
    }

    public void deleteGame(UUID id) {
        for (GameOnlineDTO element : listLobby) {
            if (element.getId().equals(id)) listLobby.remove(element);
            break;
        }
    }

    public String userHit(GameOnlineDTO gameOnlineDTO) {
        if (gameOnlineDTO.getPlayer1() == null) {
            for (GameOnlineDTO element : listGame) {
                if (element.getId().equals(gameOnlineDTO.getId())) {
                    switch (getShootResult(element.getFieldPlayer1(), gameOnlineDTO.getX(), gameOnlineDTO.getY())) {
                        case 0:
                            return "МИМО";
                        case 1:
                            return "ПОПАЛ";
                        case 2:
                            return "УБИЛ";
                        case 3:
                            return "ИТОГ";
                    }
                }
            }
        } else {
            for (GameOnlineDTO element : listGame) {
                if (element.getId().equals(gameOnlineDTO.getId())) {
                    switch (getShootResult(element.getFieldPlayer2(), gameOnlineDTO.getX(), gameOnlineDTO.getY())) {
                        case 0:
                            return "МИМО";
                        case 1:
                            return "ПОПАЛ";
                        case 2:
                            return "УБИЛ";
                        case 3:
                            return "ИТОГ";
                    }
                }
            }
        }
        return "";
    }


    public boolean inBoard(int x, int y) {
        return x < 10 && x > -1 && y < 10 && y > -1;
    }

    public int getShootResult(int[][] pole, int x, int y) {
        if (pole[x][y] != 1) {
            pole[x][y] = -2;
            return 0;
        } else {
            pole[x][y] = -1;
            if (isDead(pole, x, y)) {
                if (isWin(pole)) {
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
        return !inBoard(x, y - n) || pole[x][y - n] != 1;
    }

}
