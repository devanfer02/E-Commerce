package com.example.ecommerce.user;

import com.example.ecommerce.configs.Type;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet res, int rows) throws SQLException {
        User user = new User();

        user.setId(res.getInt("id"));
        user.setFirst_name(res.getString("first_name"));
        user.setLast_name(res.getString("last_name"));
        user.setEmail(res.getString("email"));
        user.setPhone_number(res.getString("phone_number"));
        user.setType(res.getString("type").equals("buyer") ? Type.BUYER : Type.SELLER);

        return user;
    }
}
