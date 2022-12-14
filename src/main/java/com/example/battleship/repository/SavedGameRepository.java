package com.example.battleship.repository;

import com.example.battleship.model.SavedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SavedGameRepository extends JpaRepository<SavedGame, UUID> {
    List<SavedGame> findAllByUserId (Long userId);

    Optional<SavedGame> findByNameGameAndUserId(String nameGame, Long userId);
}
