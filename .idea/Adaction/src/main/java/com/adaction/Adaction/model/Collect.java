package com.adaction.Adaction.model;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Collect {
    private int id;
    private int CityId;
    private int volunteerId; // Peut être null
    private Date date;
    private List<Collect_waste> wastes; // Liste des déchets collectés

    // Constructeur
    public Collect(int id, int CityId, int volunteerId, Date date, List<Collect_waste> wastes) {
        this.id = id;
        this.CityId = CityId;
        this.volunteerId = volunteerId;
        this.date = date;
        this.wastes = wastes;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCityId() { return CityId; }
    public void setCityId(int CityId) { this.CityId = CityId; }
    public int getVolunteerId() { return volunteerId; }
    public void setVolunteerId(int volunteerId) { this.volunteerId = volunteerId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public List<Collect_waste> getWastes() { return wastes; }
    public void setWastes(List<Collect_waste> wastes) { this.wastes = wastes; }
}
