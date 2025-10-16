package com.adaction.Adaction.repository;

import com.adaction.Adaction.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepository {

    private final DataSource dataSource;


    @Autowired
    public CityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                City city = new City(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur SQL lors de la récupération des villes", e);
        }

        return cities;
    }
    //  Récupère une ville
    public City findByName(String name) {
        City city = null;
        String sql = "SELECT * FROM City WHERE name = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    city = new City(rs.getInt("id"), rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }
    public City findById(int cityId) {
        City city = null;
        String sql = "SELECT * FROM City WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    city = new City(rs.getInt("id"), rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (city == null) {
            city = new City(0, "Inconnue");
        }

        return city;
    }

    public City save(City city) {
        if (city == null || city.getName() == null || city.getName().isEmpty()) {
            throw new RuntimeException("La ville doit avoir un nom valide");
        }

        // Chercher si la ville existe déjà
        City existingCity = findByName(city.getName());
        if (existingCity != null && existingCity.getId() > 0) {
            return existingCity; // déjà existante en base
        }

        // Sinon on insère
        String sql = "INSERT INTO City (name) VALUES (?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, city.getName());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    city.setId(rs.getInt(1));
                    System.out.println("Ville insérée avec ID = " + city.getId());
                } else {
                    throw new RuntimeException("Impossible de récupérer l'ID généré pour la ville " + city.getName());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur SQL lors de l'insertion de la ville", e);
        }

        return city;
    }


}