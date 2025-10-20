package com.adaction.Adaction.dto;

public class LoginResponse {
    private String message;
    private Long volunteerId;

    // Constructeur
    public LoginResponse(String message, Long volunteerId, String role) {
        this.message = message;
        this.volunteerId = volunteerId;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }
}