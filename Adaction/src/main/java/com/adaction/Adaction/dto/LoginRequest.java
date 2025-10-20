package com.adaction.Adaction.dto;

public class LoginRequest {
    private String mail;
    private String password;

    // Getters et setters
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
