package com.adaction.Adaction.repository;


import com.adaction.Adaction.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class CityRepository {
    private final DataSource dataSource;

    @Autowired
    public CityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Récupère une ville
    public City findById(int cityId) {
        City city = null;

        String sql = "SELECT * FROM City WHERE id = ?";

        try (Connection con = dataSource.getConnection();
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

        /*// Si pas trouvée
        if (city == null) {
            city = new City(0, "Inconnue");
        }*/

        return city;
    }
}
