package com.adaction.Adaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;


@SpringBootApplication
public class AdactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdactionApplication.class, args);
        try
        {
            //étape 1: charger la classe driver
            Class.forName("com.mysql.jdbc.Driver");

            //étape 2: créer l'objet de connexion
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3309/emp?useSSL=false", "user", "");

            //étape 3: créer l'objet statement
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, nom, age, adresse FROM inscription";
            ResultSet res = stmt.executeQuery(sql);

            //étape 5: extraire les données
            while(res.next()){
                //Récupérer par nom de colonne
                int id = res.getInt("id");
                String nom = res.getString("nom");
                int age = res.getInt("age");
                String adresse = res.getString("adresse");

                //Afficher les valeurs
                System.out.print("ID: " + id);
                System.out.print(", Nom: " + nom);
                System.out.print(", Age: " + age);
                System.out.println(", Adresse: " + adresse);
            }

            //étape 6: fermez l'objet de connexion
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}