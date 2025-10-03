package com.adaction.Adaction.model;




public class Volunteers {
   private String firstname;
   private String lastname;
   private String mail;
   private String password;
   private String collect_volunteersID;
   private String city_ID;

   public Volunteers() {}

    public Volunteers(String firstname, String lastname) {
       this.firstname = firstname;
       this.lastname = lastname;
    }
    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}
}
