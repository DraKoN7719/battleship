package com.example.battleship.web;

import com.example.battleship.model.dto.GameOnlineDTO;
import com.example.battleship.model.dto.MessageDTO;
import com.example.battleship.service.GameOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.spel.spi.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.util.List;
import java.util.UUID;

@RestController
public class GameController {

    private final GameOnlineService gameOnlineService;
    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    WebSocketMessageBrokerStats webSocketServerSockJsSession;

    public GameController(GameOnlineService gameOnlineService) {
        this.gameOnlineService = gameOnlineService;
    }

    @GetMapping("/api/getListGame")
    public ResponseEntity<List<GameOnlineDTO>> getListGame() {
        return ResponseEntity.ok(gameOnlineService.getListGame());
    }

    @GetMapping("/api/saveGameHistory")
    public void addHistoryGame(@RequestParam UUID idGame, @RequestParam long idUser) {
        gameOnlineService.addHistoryGame(idGame, idUser);
    }

    @MessageMapping("/game")
    public void processMessage(@Payload GameOnlineDTO gameOnlineDTO) {
        switch (gameOnlineDTO.getStatus()) {
            case "JOIN": {
                gameOnlineDTO.setStatus("");
                gameOnlineService.addGameOnline(gameOnlineDTO);
                template.convertAndSendToUser(gameOnlineDTO.getId().toString(), "/game",
                        new MessageDTO("JOIN", gameOnlineDTO.getPlayer1(), "", "", -1, -1));
                break;
            }
            case "JOIN_PLAYER_2": {
                gameOnlineDTO.setStatus("START_GAME");
                gameOnlineService.setGameOnline(gameOnlineDTO);
                template.convertAndSendToUser(gameOnlineDTO.getId().toString(), "/game",
                        new MessageDTO("JOIN_PLAYER_2", gameOnlineDTO.getPlayer2(), "", "", -1, -1));
                gameOnlineService.deleteGameOnline(gameOnlineDTO.getId());
                break;
            }
            case "MOTION": {
                template.convertAndSendToUser(gameOnlineDTO.getId().toString(), "/game",
                        new MessageDTO(gameOnlineService.userHit(gameOnlineDTO), gameOnlineDTO.getPlayer1() == null ? gameOnlineDTO.getPlayer2() : gameOnlineDTO.getPlayer1(),
                                "", "", gameOnlineDTO.getX(), gameOnlineDTO.getY()));
                break;
            }
            case "DISCONNECTION": {
                System.out.println("DISCONNECTION");
                template.convertAndSendToUser(gameOnlineDTO.getId().toString(), "/game",
                        new MessageDTO("DISCONNECTION", gameOnlineDTO.getPlayer1() == null ? gameOnlineDTO.getPlayer2() : gameOnlineDTO.getPlayer1(),
                                "", "", -1, -1));
                gameOnlineService.deleteGame(gameOnlineDTO.getId());
                break;
            }
        }

    }
}
