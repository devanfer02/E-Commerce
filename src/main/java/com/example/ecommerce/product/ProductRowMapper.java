package com.example.ecommerce.product;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet res, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(res.getInt("id"));
        product.setSeller(res.getInt("seller_id"));
        product.setTitle(res.getString("title"));
        product.setDescription(res.getString("description"));
        product.setPrice(res.getLong("price"));
        product.setStock(res.getInt("price"));

        return  product;
    }
}
