package com.example.battleship.model.dto;

public class MessageDTO {
    private String status;
    private long id;
    private String field;
    private String resultAttack;
    private int x;
    private int y;

    public MessageDTO(String status, long id, String field, String resultAttack, int x, int y) {
        this.status = status;
        this.id = id;
        this.field = field;
        this.resultAttack = resultAttack;
        this.x = x;
        this.y = y;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getResultAttack() {
        return resultAttack;
    }

    public void setResultAttack(String resultAttack) {
        this.resultAttack = resultAttack;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
