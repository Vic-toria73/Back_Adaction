package com.adaction.Adaction.dto;

public class LoginRequest {
    private String firstname;
    private String password;

    // Getters et setters
    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
