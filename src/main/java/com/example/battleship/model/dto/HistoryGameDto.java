package com.example.battleship.model.dto;

public class HistoryGameDto {
    private String id;
    private Long player1;
    private String login1;
    private Long player2;
    private String login2;
    private Long result;

    public HistoryGameDto(String id, Long player1, String login1, Long player2, String login2, Long result) {
        this.id = id;
        this.player1 = player1;
        this.login1 = login1;
        this.player2 = player2;
        this.login2 = login2;
        this.result = result;
    }

    public HistoryGameDto(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPlayer1() {
        return player1;
    }

    public void setPlayer1(Long player1) {
        this.player1 = player1;
    }

    public String getLogin1() {
        return login1;
    }

    public void setLogin1(String login1) {
        this.login1 = login1;
    }

    public Long getPlayer2() {
        return player2;
    }

    public void setPlayer2(Long player2) {
        this.player2 = player2;
    }

    public String getLogin2() {
        return login2;
    }

    public void setLogin2(String login2) {
        this.login2 = login2;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
