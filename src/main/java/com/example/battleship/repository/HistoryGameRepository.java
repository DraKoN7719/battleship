package com.example.battleship.repository;

import com.example.battleship.model.HistoryGame;
import com.example.battleship.model.dto.HistoryGameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HistoryGameRepository extends JpaRepository<HistoryGame, UUID> {

    @Query(value = """
                   SELECT CAST(game_id as varchar) as id, player1, l1.login as login1, player2, l2.login as login2, "result" FROM history_game hg
                   JOIN "user" l1 ON l1.user_id = hg.player1
                   JOIN "user" l2 ON l2.user_id = hg.player2 WHERE player1 = :userId OR player2 = :userId
                   """,
        nativeQuery = true)
    List<HistoryGameProjection> findAllByUserId(@Param("userId") Long userId);
}
