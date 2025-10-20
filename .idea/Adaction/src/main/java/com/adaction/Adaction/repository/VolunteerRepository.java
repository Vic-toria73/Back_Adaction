package com.adaction.Adaction.repository;

import com.adaction.Adaction.model.City;
import com.adaction.Adaction.model.Volunteers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VolunteerRepository {

    private final CityRepository cityRepository;
    private final DataSource dataSource;

    @Autowired
    public VolunteerRepository(CityRepository cityRepository, DataSource dataSource) {
        this.cityRepository = cityRepository;
        this.dataSource = dataSource;
    }

    public List<Volunteers> findAll() {
        List<Volunteers> volunteersList = new ArrayList<>();
        String sql = "SELECT * FROM volunteers";

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int cityId = rs.getInt("city_ID");
                // Utiliser findById pour récupérer la ville par son ID
                Optional<City> cityOpt = Optional.ofNullable(cityRepository.findById(cityId));
                City city = cityOpt.orElseThrow(() -> new RuntimeException("Ville introuvable avec ID = " + cityId));

                Volunteers volunteer = new Volunteers(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("mail"),
                        rs.getString("password"),
                        city
                );
                volunteersList.add(volunteer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return volunteersList;
    }

    public Volunteers save(Volunteers volunteer) {
        // Vérifier que le bénévole a une ville valide
        City city = volunteer.getCity();
        if (city == null || city.getId() <= 0) {
            throw new RuntimeException("Le bénévole doit avoir une ville avec un ID valide.");
        }

        // Vérifier si la ville existe déjà en DB
        City cityFromDb = cityRepository.findById(city.getId()); // retourne null si non trouvé
        if (cityFromDb != null) {
            volunteer.setCity(cityFromDb);
        } else {
            // Ville inexistante → insérer dans DB
            City savedCity = cityRepository.save(city);
            if (savedCity.getId() <= 0) {
                throw new RuntimeException("Impossible d'insérer la ville avec ID fourni : " + city.getId());
            }
            volunteer.setCity(savedCity);
            System.out.println("Ville créée avec ID = " + savedCity.getId());
        }

        // Préparer l'insertion du bénévole dans la DB
        String sql = "INSERT INTO volunteers (firstname, lastname, mail, password, city_ID, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, NOW(), NOW())";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, volunteer.getFirstname());
            pstmt.setString(2, volunteer.getLastname());
            pstmt.setString(3, volunteer.getMail());
            pstmt.setString(4, volunteer.getPassword());
            pstmt.setInt(5, volunteer.getCity().getId());

            pstmt.executeUpdate();

            // Récupérer l'ID généré pour le bénévole
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    volunteer.setId(rs.getInt(1));
                    System.out.println("Bénévole inséré avec ID = " + volunteer.getId());
                } else {
                    throw new RuntimeException("Impossible de récupérer l'ID du bénévole");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur SQL lors de l'insertion du bénévole", e);
        }

        return volunteer;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM volunteers WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0; // true si un benevole a été supprimé

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//pour récuperer un bénévole par son email
    public Optional<Volunteers> findByMail(String mail) {
        String sql = "SELECT * FROM volunteers WHERE mail = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, mail);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int cityId = rs.getInt("city_ID");
                    Optional<City> cityOpt = Optional.ofNullable(cityRepository.findById(cityId));
                    City city = cityOpt.orElseThrow(() -> new RuntimeException("Ville introuvable avec ID = " + cityId));
                    Volunteers volunteer = new Volunteers(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("mail"),
                            rs.getString("password"),
                            city
                    );
                    return Optional.of(volunteer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}