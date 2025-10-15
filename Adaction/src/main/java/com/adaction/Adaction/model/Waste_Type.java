package com.adaction.Adaction.model;




public class Waste_Type {
    private int id;
    private String value;
    private String label;
    private String className;

    public Waste_Type(int id, String value, String label, String className) {
        this.id = id;
        this.value = value;
        this.label = label;
        this.className = className;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}
