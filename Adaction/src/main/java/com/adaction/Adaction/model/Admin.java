package com.adaction.Adaction.model;

public class Admin {
    private int id;
    private int city_id;
    private int volunteer_id;

    public Admin() {}

    public Admin(int id, int city_id, int volunteer_id) {
        this.id = id;
        this.city_id = city_id;
        this.volunteer_id = volunteer_id;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id;}

    public int getCity_id() { return city_id;}
    public void setCity_id(int city_id) { this.city_id = city_id; }

    public int getVolunteer_id() { return volunteer_id;}
    public void setVolunteer_id(int volunteer_id) { this.volunteer_id = volunteer_id;}
}
