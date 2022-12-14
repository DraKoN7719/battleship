package com.example.battleship.model.dto;

public interface HistoryGameProjection {

    String getId();
    Long getPlayer1();
    String getLogin1();
    Long getPlayer2();
    String getLogin2();
    Long getResult();
}
