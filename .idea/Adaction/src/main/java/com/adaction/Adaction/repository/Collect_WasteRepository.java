package com.adaction.Adaction.repository;

import com.adaction.Adaction.model.Collect_waste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Collect_WasteRepository {
    private final DataSource dataSource;

    @Autowired
    public Collect_WasteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Exemple : Trouver tous les déchets pour une collecte donnée
    public List<Collect_waste> findByCollectId(int collectId) {
        List<Collect_waste> wastes = new ArrayList<>();
        String query = "SELECT * FROM Collect_waste WHERE collect_id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, collectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                wastes.add(new Collect_waste(
                        rs.getInt("id"),
                        rs.getInt("collect_id"),
                        rs.getInt("waste_type_id"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wastes;
    }

    // Exemple : Sauvegarder un déchet (utilisé dans CollectRepository)
    public void save(Collect_waste waste) {
        String query = "INSERT INTO Collect_waste (collect_id, WasteTypeid, quantity) VALUES (?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, waste.getCollectId());
            stmt.setInt(2, waste.getWasteTypeId());
            stmt.setInt(3, waste.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}