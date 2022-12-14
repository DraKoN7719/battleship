package com.example.battleship.repository;

import com.example.battleship.model.Placement;
import com.example.battleship.model.PlacementPK;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, PlacementPK> {
    List<Placement> findAllByUserIdOrderByPlacementName(Long userId);

    Optional<Placement> findPlacementByUserIdAndPlacementName(@NotNull Long userId, @NotNull String placementName);

    void deleteByUserIdAndPlacementName(@NotNull Long userId, @NotNull String placementName);
}
