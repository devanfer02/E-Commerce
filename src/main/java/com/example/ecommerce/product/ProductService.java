package com.example.ecommerce.product;

import com.example.ecommerce.configs.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final Database db;

    @Autowired
    public ProductService(Database db) {
        this.db = db;
    }

    public List<Product> getProducts() {
        String query = "SELECT * FROM products";

        return db.fetch(query, null, new ProductRowMapper());
    }

    public Product getProductById(Integer id) {
        String query = "SELECT * FROM products WHERE id=?";

        return db.fetchByOne(query, new Object[]{id}, new ProductRowMapper());
    }

}
