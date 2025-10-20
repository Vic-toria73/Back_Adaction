package com.adaction.Adaction.model;

public class Collect_waste {
    private int id;
    private int CollectId;
    private int wasteTypeid;
    private int quantity;

    // Constructeur vide
    public Collect_waste() {}

    // Constructeur
    public Collect_waste(int id, int CollectId, int waste_Type_id, int quantity) {
        this.id = id;
        this.CollectId = CollectId;
        this.wasteTypeid = waste_Type_id;
        this.quantity = quantity;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCollectId() { return CollectId; }
    public void setCollectId(int collectId) { this.CollectId = collectId; }
    public int getWasteTypeId() { return wasteTypeid; }
    public void setWasteTypeId(int waste_Type_id) {this.wasteTypeid = waste_Type_id;
    }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}