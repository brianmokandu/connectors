package io.credable.connectors.services.impl;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseService {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean testConnection(String schemaName) {
        try {
            var t =jdbcTemplate.queryForObject("SELECT * FROM %s LIMIT 1".formatted(schemaName), Object.class);
            System.out.println(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

