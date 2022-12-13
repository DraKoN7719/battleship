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
        if (listLobby.stream().filter(x -> x.getId().equals(gameOnlineDTO.getId())).count() == 0)
            listLobby.add(gameOnlineDTO);
    }

    public void setGameOnline(GameOnlineDTO gameOnlineDTO) {
        listLobby.stream().filter(x -> x.getId().equals(gameOnlineDTO.getId())).forEach(x -> {
            x.setPlayer2(gameOnlineDTO.getPlayer2());
            x.setFieldPlayer2(gameOnlineDTO.getFieldPlayer2());
            x.setStatus(gameOnlineDTO.getStatus());
        });
    }

    public void deleteGameOnline(UUID id) {
        listLobby.stream().filter((x) -> x.getId().equals(id)).forEach(listGame::add);
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
}
