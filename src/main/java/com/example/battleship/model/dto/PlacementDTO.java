package com.example.battleship.model.dto;

public class PlacementDTO {
    private Long userId;
    private int[][] placement;
    private String placementName;

    public PlacementDTO(String placementName, Long userId, int[][] placement) {
        this.placementName = placementName;
        this.userId = userId;
        this.placement = placement;
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int[][] getPlacement() {
        return placement;
    }

    public void setPlacement(int[][] placement) {
        this.placement = placement;
    }

}
