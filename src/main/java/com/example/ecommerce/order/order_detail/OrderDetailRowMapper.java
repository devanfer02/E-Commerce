package com.example.ecommerce.order.order_detail;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {

    @Override
    public OrderDetail mapRow(ResultSet res, int rowNum) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrder(res.getInt("order_id"));
        orderDetail.setProduct(res.getInt("product_id"));
        orderDetail.setQuantity(res.getInt("quantity"));
        orderDetail.setPrice(res.getDouble("price"));

        return orderDetail;
    }
}
