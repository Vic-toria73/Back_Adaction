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

    public List<Collect> findAll() {
        List<Collect> collects = new ArrayList<>();
        String query = "SELECT c.id AS collect_id, c.City_id AS city_id, c.volunteer_id, c.date, " +
                "cw.id AS waste_id, cw.Waste_Type_id AS waste_type_id, cw.quantity " +
                "FROM Collect c " +
                "LEFT JOIN Collect_waste cw ON c.id = cw.collect_id";

        try (Connection con = dataSource.getConnection();
             Statement smt = con.createStatement();
             ResultSet rs = smt.executeQuery(query)) {

            // Utilise une Map pour regrouper les déchets par collecte
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

                // Ajoute le déchet à la collecte
                if (rs.getInt("collect_waste_id") > 0) {
                    collect.getWastes().add(new Collect_waste(
                            rs.getInt("collect_waste_id"),
                            collectId,
                            rs.getInt("waste_type_id"),
                            rs.getInt("quantity")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collects;
    }

    public Collect save(Collect collect) {
        String insertCollectQuery = "INSERT INTO Collect (city_id, volunteer_id, date) VALUES (?, ?, ?)";
        String insertCollectWasteQuery = "INSERT INTO Collect_waste (collect_id, waste_type_id, quantity) VALUES (?, ?, ?)";
        try (Connection con = dataSource.getConnection()) {
            // Désactive l'auto-commit pour gérer la transaction manuellement
            con.setAutoCommit(false);
            try (PreparedStatement collectStmt = con.prepareStatement(insertCollectQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement collectWasteStmt = con.prepareStatement(insertCollectWasteQuery)) {
                // Insertion de la collecte
                collectStmt.setInt(1, collect.getCityId());
                collectStmt.setObject(2, collect.getVolunteerId(), Types.INTEGER);
                collectStmt.setDate(3, new java.sql.Date(collect.getDate().getTime()));
                collectStmt.executeUpdate();
                // Récupération de l'ID généré pour la collecte
                ResultSet generatedKeys = collectStmt.getGeneratedKeys();
                int collectId = -1;
                if (generatedKeys.next()) {
                    collectId = generatedKeys.getInt(1);
                }
                // Insertion des déchets associés
                for (Collect_waste waste : collect.getWastes()) {
                    collectWasteStmt.setInt(1, collectId);
                    collectWasteStmt.setInt(2, waste.getWaste_Type_Id());
                    collectWasteStmt.setInt(3, waste.getQuantity());
                    collectWasteStmt.executeUpdate();
                }
                // Valide la transaction
                con.commit();
                // Retourne la collecte sauvegardée
                return new Collect(collectId, collect.getCityId(), collect.getVolunteerId(), collect.getDate(), collect.getWastes());
            } catch (SQLException e) {
                // Annule la transaction en cas d'erreur
                con.rollback();
                e.printStackTrace();
                return null;
            } finally {
                // Réactive l'auto-commit
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}