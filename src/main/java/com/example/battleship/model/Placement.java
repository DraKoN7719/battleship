package com.example.battleship.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "placement")
@IdClass(PlacementPK.class)
public class Placement {
    @Id
    @Column(name = "user_id")
    private long userId;
    @Id
    @Column(name = "placement")
    private String placement;
    @Column(name = "placement_name")
    private String placementName;


    public Placement(String placementName, long userId, String placement) {
        this.placementName = placementName;
        this.userId = userId;
        this.placement = placement;
    }

    public Placement() {
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Placement placement1 = (Placement) o;
        return userId == placement1.userId && Objects.equals(placement, placement1.placement) && Objects.equals(placementName, placement1.placementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, placement, placementName);
    }
}
