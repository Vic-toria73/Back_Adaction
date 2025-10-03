package com.adaction.Adaction.repository;


import com.adaction.Adaction.model.Volunteers;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VolunteerRepository {
    private static final String URL = "jdbc:mysql://localhost:3309/example-database";
    private static final String USER = "user";
    private static final String PASSWORD = "mypassword";

    public List<Volunteers> findAll() {
        List<Volunteers> volunteersList = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM volunteers")) {
            while (rs.next()) {
                Volunteers volunteer= new Volunteers(
                        rs.getString("firstname"),
                        rs.getString("lastname")
                );
                volunteersList.add(volunteer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  volunteersList;
    }
}
