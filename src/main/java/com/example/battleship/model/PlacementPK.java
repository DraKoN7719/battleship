package com.example.battleship.model;

import java.io.Serializable;
import java.util.Objects;

public class PlacementPK implements Serializable {

    private Long userId;
    private String placement;

    public PlacementPK(long userId, String placement) {
        this.userId = userId;
        this.placement = placement;
    }

    public PlacementPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacementPK that = (PlacementPK) o;
        return userId == that.userId && Objects.equals(placement, that.placement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, placement);
    }
}
