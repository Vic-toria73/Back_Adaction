package com.adaction.Adaction.model;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Collect {
    private int id;
    private int cityId;
    private int volunteerId; // Peut être null
    private Date date;
    private List<Collect_waste> wastes; // Liste des déchets collectés

    // Constructeur
    public Collect(int id, int cityId, int volunteerId, Date date, List<Collect_waste> wastes) {
        this.id = id;
        this.cityId = cityId;
        this.volunteerId = volunteerId;
        this.date = date;
        this.wastes = wastes;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCityId() { return cityId; }
    public void setCityId(int cityId) { this.cityId = cityId; }
    public int getVolunteerId() { return volunteerId; }
    public void setVolunteerId(int volunteerId) { this.volunteerId = volunteerId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public List<Collect_waste> getWastes() { return wastes; }
    public void setWastes(List<Collect_waste> wastes) { this.wastes = wastes; }
}
