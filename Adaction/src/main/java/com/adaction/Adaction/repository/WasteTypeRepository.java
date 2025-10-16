package com.adaction.Adaction.repository;

import com.adaction.Adaction.model.Waste_Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WasteTypeRepository {

    private final DataSource dataSource;

    @Autowired
    public WasteTypeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Waste_Type> findAll() {
        List<Waste_Type> waste_Types = new ArrayList<>();
        String query = "SELECT * FROM Waste_Type";

        try (Connection con = dataSource.getConnection();
             Statement smt = con.createStatement();
             ResultSet rs = smt.executeQuery(query)) {

            while (rs.next()) {
                Waste_Type waste_Type = new Waste_Type(
                        rs.getInt("id"),
                        rs.getString("value"),
                        rs.getString("label"),
                        rs.getString("className")
                );
                waste_Types.add(waste_Type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waste_Types;
    }
}