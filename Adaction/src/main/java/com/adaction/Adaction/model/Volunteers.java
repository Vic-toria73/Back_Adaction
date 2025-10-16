package com.adaction.Adaction.model;


import org.checkerframework.checker.units.qual.C;

public class Volunteers {
    private int id;
    private String firstname;
    private String lastname;
    private String mail;
    private String password;
   // private int collect_volunteer_ID;
    private City city;

    public Volunteers() {}

    public Volunteers(int id, String firstname, String lastname, String mail, String password, City city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
       // this.collect_volunteer_ID = collect_volunteer_ID;
        this.city = city;
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

   // public int getCollect_volunteerID() { return collect_volunteer_ID; }
   // public void setCollect_volunteerID(int collect_volunteer_ID) { this.collect_volunteer_ID = collect_volunteer_ID; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public int getCityId() {
        return city != null ? city.getId() : 0;
    }
}