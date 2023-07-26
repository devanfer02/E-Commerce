package com.example.ecommerce.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class Database {
    private final JdbcTemplate jdbc;

    @Autowired
    public Database(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("PRAGMA foreign_keys = ON;");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
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

    public boolean findOneInTable(String table, Object[] params) {
        String query = String.format("SELECT COUNT(*) FROM %s WHERE id = ?", table);
        int count = jdbc.queryForObject(query, Integer.class, params);

        return count > 0;
    }

    public boolean findOneInTable(String table, String column, Object[] params) {
        String query = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", table, column);
        int count = jdbc.queryForObject(query, Integer.class, params);

        return count > 0;
    }

    @SuppressWarnings("deprecation")
    public <T> T fetchByIdInTable(String table, Integer id, RowMapper<T> mapper) {
        String query = String.format("SELECT * FROM %s WHERE id = ?", table);
        return jdbc.queryForObject(query, new Object[]{id}, mapper);
    }

    public int remove(String table, Object[] params) {
        String query = String.format("DELETE FROM %s WHERE id = ?",table);
        int rowAffected = jdbc.update(query, params);

        return rowAffected;
    }
}
