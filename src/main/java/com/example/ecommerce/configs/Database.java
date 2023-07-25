package com.example.ecommerce.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Database {
    private final JdbcTemplate jdbc;

    @Autowired
    public Database(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @SuppressWarnings("deprecation")
    public <T> List<T> fetch(String query, Object[] params, RowMapper<T> mapper) {
        return jdbc.query(query, params, mapper);
    }

    @SuppressWarnings("deprecation")
    public <T> T fetchByOne(String query, Object[] params, RowMapper<T> mapper) {
        return jdbc.queryForObject(query, params, mapper);
    }

    public int insert(String query, Object[] params) {
        int rowAffected = jdbc.update(query, params);
        return rowAffected;
    }
}
