package com.example.battleship.exception;

public class SetOrCreateGameException extends RuntimeException {

    private Long playerId;

    public SetOrCreateGameException(String message, Long playerId) {
        super(message);
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }
}
