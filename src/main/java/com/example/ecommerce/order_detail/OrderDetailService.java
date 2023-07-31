package com.example.ecommerce.order_detail;

import com.example.ecommerce.configs.Database;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailService {
    private Database db;

    @Autowired
    public OrderDetailService(Database db) {
        this.db = db;
    }
}
