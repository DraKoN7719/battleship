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

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }

    public String getUserName() {
        return login;
    }

    public void setUserName(String userName) {
        this.login = userName;
    }

    public StatusAuth getStatus() {
        return status;
    }

    public void setStatus(StatusAuth status) {
        this.status = status;
    }
}
