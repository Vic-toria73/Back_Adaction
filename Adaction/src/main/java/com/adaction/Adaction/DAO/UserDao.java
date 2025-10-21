package com.adaction.Adaction.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> findAdminByFirstname(String firstname) {
        String sql = "SELECT * FROM Admin WHERE firstname = ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, firstname);
        return results.isEmpty() ? null : results.get(0);
    }

    public Map<String, Object> findVolunteersByFirstname(String firstname) {
        String sql = "SELECT * FROM volunteers WHERE firstname = ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, firstname);
        return results.isEmpty() ? null : results.get(0);
    }
}