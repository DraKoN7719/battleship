package com.example.battleship.repository;

import com.example.battleship.model.HistoryGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HistoryGameRepository extends JpaRepository<HistoryGame, UUID> {

    @Query(value = """
                   SELECT * FROM history_game WHERE player1 = :userId OR player2 = :userId
                   """,
        nativeQuery = true)
    List<HistoryGame> findAllByUserId(@Param("userId") Long userId);
}
