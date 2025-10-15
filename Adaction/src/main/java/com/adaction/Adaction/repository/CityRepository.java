package com.adaction.Adaction.repository;


import com.adaction.Adaction.model.City;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CityRepository {

    private static final String URL = "jdbc:mariadb://localhost:3309/example-database";
    private static final String USER = "user";
    private static final String PASSWORD = "mypassword";


    // Récupère une ville
    public City findById(int cityId) {
        City city = null;

        String sql = "SELECT * FROM City WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cityId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    city = new City(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si pas trouvée
        if (city == null) {
            city = new City(0, "Inconnue");
        }

        return city;
    }
}
