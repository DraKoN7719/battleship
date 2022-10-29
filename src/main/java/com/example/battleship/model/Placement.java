package com.example.battleship.model;

public class Placement {

    private String placementName;
    private long user_id;
    private String placement;

    public Placement(String placementName, long user_id, String placement) {
        this.placementName = placementName;
        this.user_id = user_id;
        this.placement = placement;
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
