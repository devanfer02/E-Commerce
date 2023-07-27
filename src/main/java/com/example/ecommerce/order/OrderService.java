package com.example.ecommerce.order;

import com.example.ecommerce.configs.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final Database db;

    @Autowired
    public OrderService(Database db) { this.db = db; }
}
