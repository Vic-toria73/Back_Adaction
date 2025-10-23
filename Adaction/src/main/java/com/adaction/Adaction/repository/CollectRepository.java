package com.adaction.Adaction.repository;

import com.adaction.Adaction.model.Collect;
import com.adaction.Adaction.model.Collect_waste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CollectRepository {
    private final DataSource dataSource;

    @Autowired
    public CollectRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Récupère toutes les collectes avec leurs déchets associés.
     */
    public List<Collect> findAll() {
        List<Collect> collects = new ArrayList<>();
        String query = "SELECT c.id AS collect_id, c.city_id AS city_id, c.volunteer_id, c.date, " +
                "cw.id AS waste_id, cw.Waste_Type_id AS Waste_Type_id, cw.quantity " +
                "FROM Collect c " +
                "LEFT JOIN Collect_waste cw ON c.id = cw.collect_id";

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            Map<Integer, Collect> collectMap = new HashMap<>();

            while (rs.next()) {
                int collectId = rs.getInt("collect_id");
                Collect collect = collectMap.get(collectId);

                if (collect == null) {
                    // Nouvelle collecte
                    collect = new Collect(
                            collectId,
                            rs.getInt("city_id"),
                            rs.getInt("volunteer_id"),
                            rs.getDate("date"),
                            new ArrayList<>()
                    );
                    collectMap.put(collectId, collect);
                    collects.add(collect);
                }

                // Ajoute le déchet à la collecte (s’il y en a un)
                if (rs.getObject("waste_id") != null) {
                    collect.getWastes().add(new Collect_waste(
                            rs.getInt("waste_id"),
                            collectId,
                            rs.getInt("Waste_Type_id"),
                            rs.getInt("quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collects;
    }
    public List<Collect> findByVolunteerId(int volunteerId) {
        List<Collect> collects = new ArrayList<>();
        String query = "SELECT c.id AS collect_id, c.city_id AS city_id, c.volunteer_id, c.date, " +
                "cw.id AS waste_id, cw.Waste_Type_id AS Waste_Type_id, cw.quantity " +
                "FROM Collect c " +
                "LEFT JOIN Collect_waste cw ON c.id = cw.collect_id " +
                "WHERE c.volunteer_id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, volunteerId);
            ResultSet rs = stmt.executeQuery();

            Map<Integer, Collect> collectMap = new HashMap<>();

            while (rs.next()) {
                int collectId = rs.getInt("collect_id");
                Collect collect = collectMap.get(collectId);

                if (collect == null) {
                    collect = new Collect(
                            collectId,
                            rs.getInt("city_id"),
                            rs.getInt("volunteer_id"),
                            rs.getDate("date"),
                            new ArrayList<>()
                    );
                    collectMap.put(collectId, collect);
                    collects.add(collect);
                }

                if (rs.getObject("waste_id") != null) {
                    collect.getWastes().add(new Collect_waste(
                            rs.getInt("waste_id"),
                            collectId,
                            rs.getInt("Waste_Type_id"),
                            rs.getInt("quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collects;
    }
    /**
     * Sauvegarde une collecte et ses déchets associés en une seule transaction.
     */
    public Collect save(Collect collect) {
        String insertCollectQuery = "INSERT INTO Collect (city_id, volunteer_id, date) VALUES (?, ?, ?)";
        String insertCollectWasteQuery = "INSERT INTO Collect_waste (Collect_id, Waste_Type_id, quantity) VALUES (?, ?, ?)";

        try (Connection con = dataSource.getConnection()) {
            // 🔒 Désactivation de l’auto-commit pour gérer la transaction
            con.setAutoCommit(false);

            try (
                    PreparedStatement collectStmt = con.prepareStatement(insertCollectQuery, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement collectWasteStmt = con.prepareStatement(insertCollectWasteQuery)
            ) {
                // 🔹 Insertion de la collecte
                collectStmt.setInt(1, collect.getCityId());
                collectStmt.setObject(2, collect.getVolunteerId(), Types.INTEGER);
                collectStmt.setDate(3, new java.sql.Date(collect.getDate().getTime()));
                collectStmt.executeUpdate();

                // 🔹 Récupération de l’ID généré
                int collectId = 1;
                try (ResultSet generatedKeys = collectStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        collectId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Échec de la création de la collecte, aucun ID généré.");
                    }
                }
                for (Collect_waste waste : collect.getWastes()) {
                    System.out.println("ID: " + waste.getId() + ", Type: " + waste.getWasteTypeId() + ", Quantité: " + waste.getQuantity());
                }
                // 🔹 Insertion des déchets associés
                for (Collect_waste waste : collect.getWastes()) {
                    collectWasteStmt.setInt(1, collectId);
                    collectWasteStmt.setInt(2, waste.getWasteTypeId());
                    collectWasteStmt.setInt(3, waste.getQuantity());
                    System.out.println(collectWasteStmt);
                    collectWasteStmt.executeUpdate();
                }

                // ✅ Validation finale de la transaction
                con.commit();

                // Retourne la collecte sauvegardée (avec son nouvel ID)
                return new Collect(
                        collectId,
                        collect.getCityId(),
                        collect.getVolunteerId(),
                        collect.getDate(),
                        collect.getWastes()
                );

            } catch (SQLException e) {
                // ❌ En cas d’erreur, on annule tout
                con.rollback();
                e.printStackTrace();
                return null;
            } finally {
                // 🔁 Réactivation du mode auto-commit
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}