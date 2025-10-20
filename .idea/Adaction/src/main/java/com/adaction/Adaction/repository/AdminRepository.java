package com.adaction.Adaction.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AdminRepository {
private final DataSource dataSource;


    @Autowired
    public AdminRepository(DataSource dataSource) {
        this.dataSource = dataSource;



}
}
