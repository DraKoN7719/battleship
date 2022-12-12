package com.example.battleship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bot")
public class Bot {

    @Id
    @Column(name = "bot_id")
    private Long id;
    @Column(name = "difficult")
    private Integer difficult;

    public Bot(Long id, Integer difficult) {
        this.id = id;
        this.difficult = difficult;
    }

    public Bot() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }


}
