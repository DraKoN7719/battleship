package com.example.battleship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "bot")
public class Bot {

    @Id
    @Column(name = "bot_id")
    private long id;
    @Column(name = "difficult")
    private int difficult;

    public Bot(long id, int difficult) {
        this.id = id;
        this.difficult = difficult;
    }

    public Bot() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }


}
