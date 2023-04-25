package io.credable.connectors.services.impl;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseService {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean testConnection() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

