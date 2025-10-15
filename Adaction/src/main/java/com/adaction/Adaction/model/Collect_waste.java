package com.adaction.Adaction.model;

public class Collect_waste {
    private int id;
    private int collectId;
    private int Waste_Type_id;
    private int quantity;

    // Constructeur vide
    public Collect_waste() {}

    // Constructeur
    public Collect_waste(int id, int collectId, int wasteTypeId, int quantity) {
        this.id = id;
        this.collectId = collectId;
        this.Waste_Type_id = wasteTypeId;
        this.quantity = quantity;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCollectId() { return collectId; }
    public void setCollectId(int collectId) { this.collectId = collectId; }
    public int getWaste_Type_Id() { return Waste_Type_id; }
    public void setWaste_Type_Id(int waste_Type_Id) {
    }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}