package com.example.ecommerce.user;

import com.example.ecommerce.configs.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final Database db;

    @Autowired
    public UserService(Database db) {
        this.db = db;
    }
    public List<User> getUsers() {
        String query = "SELECT * FROM users";
        return db.fetch(query, null, new UserRowMapper());
    }

    public List<User> getUsers(String type) {
        String query = "SELECT * FROM users WHERE type=?";
        return db.fetch(query, new Object[]{type} , new UserRowMapper());
    }

    public User getUserById(Integer id) {
        String query = "SELECT * FROM users WHERE id=?";
        return db.fetchByOne(query, new Object[]{id}, new UserRowMapper());
    }

    public int addNewUser(User user) {
        String query = "INSERT INTO users (first_name, last_name, email, phone_number, type) VALUES (?, ?, ?, ?, ?)";
        Object[] params = new Object[] {
            user.getFirst_name(),
            user.getLast_name(),
            user.getEmail(),
            user.getPhone_number(),
            user.getType().toString()
        };
        return db.insert(query, params);
    }

    public int updateUser(Integer id, User user) {
        String query = "UPDATE users SET first_name=?, last_name=?, email=?, phone_number=?, type=? WHERE id=?";
        Object[] params = new Object[] {
            user.getFirst_name(),
            user.getLast_name(),
            user.getEmail(),
            user.getPhone_number(),
            user.getType().toString(),
            id
        };
        return db.insert(query,params);
    }

    public int removeUser(Integer id) {
        String query = "DELETE FROM users WHERE id=?";
        return db.insert(query, new Object[]{ id });
    }
}
