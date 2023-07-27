package com.example.ecommerce.order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet res, int rowNum) {
        Order order = new Order();

        return null;
    }
}
