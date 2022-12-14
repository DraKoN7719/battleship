package com.example.battleship.model.dto;

public class AuthenticationDto {
    private Long id;
    private String login;
    private StatusAuth status;

    public AuthenticationDto() {
    }

    public AuthenticationDto(Long id, String login, StatusAuth status) {
        this.id = id;
        this.login = login;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long userId) {
        this.id = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String userName) {
        this.login = userName;
    }

    public StatusAuth getStatus() {
        return status;
    }

    public void setStatus(StatusAuth status) {
        this.status = status;
    }
}
