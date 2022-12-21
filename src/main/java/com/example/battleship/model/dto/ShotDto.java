package com.example.battleship.model.dto;

import com.example.battleship.model.Coordinate;

import java.util.UUID;

public class ShotDto {
    private UUID gameId;
    private Long shotAt;
    private Coordinate coordinate;

    public ShotDto() {
    }

    public ShotDto(UUID gameId, Long shotAt, Coordinate coordinate) {
        this.gameId = gameId;
        this.shotAt = shotAt;
        this.coordinate = coordinate;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public Long getShotAt() {
        return shotAt;
    }

    public void setShotAt(Long shotAt) {
        this.shotAt = shotAt;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
