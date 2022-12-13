package com.example.battleship.model.dto;

public class MessageDTO {
    private String status;
    private long id;
    private String field;
    private String resultAttack;

    public MessageDTO(String status, long id, String field, String resultAttack) {
        this.status = status;
        this.id = id;
        this.field = field;
        this.resultAttack = resultAttack;
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
}
