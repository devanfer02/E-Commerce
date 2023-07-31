package com.example.ecommerce.order.order_detail;

import com.example.ecommerce.configs.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    private Database db;

    @Autowired
    public OrderDetailService(Database db) {
        this.db = db;
    }

    public OrderDetail getDetailsByOrderId(Integer id) {
        String query = "SELECT * FROM order_details WHERE order_id=?";

        return db.fetchByOne(query, new Object[]{id}, new OrderDetailRowMapper());
    }

    public int addNewDetails(OrderDetail orderDetail) {
        String query = "INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        Object[] params = new Object[] {
                orderDetail.getOrder(),
                orderDetail.getProduct(),
                orderDetail.getQuantity(),
                orderDetail.getPrice()
        };

        return db.insert(query, params);
    }

}
