package com.adaction.Adaction.model;




public class Volunteers {
    private int id;
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
    private int city_ID;

    public Volunteers() {}

    public Volunteers(int id, String firstname, String lastname, String mail, String password, int city_ID) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        this.city_ID = city_ID;
    }

    public Volunteers(int id, String firstname, String lastname, String mail, String password, City city) {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getCity_ID() { return city_ID; }
    public void setCity_ID(int city_ID) { this.city_ID = city_ID; }
}