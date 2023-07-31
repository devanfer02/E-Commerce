package com.example.ecommerce.order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet res, int rowNum) throws SQLException {
        Order order = new Order();

        order.setId(res.getInt("id"));
        order.setBuyer(res.getInt("buyer_id"));
        order.setTotal(res.getInt("total"));
        order.setNotes(res.getString("notes"));
        order.setDiscount(res.getDouble("discount"));
        order.setOrderedAt(res.getString("ordered_at"));
        order.setPaid(res.getBoolean("is_paid"));

        return order;
    }
}
