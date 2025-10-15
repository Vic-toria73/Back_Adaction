package com.adaction.Adaction.repository;

import com.adaction.Adaction.model.City;
import com.adaction.Adaction.model.Volunteers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        try (Connection con = dataSource.getConnection();
             Statement smt = con.createStatement();
             ResultSet rs = smt.executeQuery("SELECT * FROM volunteers")) {

            while (rs.next()) {
                int cityId = rs.getInt("city_ID");
                City city = cityRepository.findById(cityId);
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
            // En production, utilise un logger plutôt que printStackTrace
            e.printStackTrace();
        }
        return volunteersList;
    }
}