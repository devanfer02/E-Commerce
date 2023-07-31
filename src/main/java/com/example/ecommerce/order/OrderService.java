package com.example.ecommerce.order;

import com.example.ecommerce.configs.Database;
import com.example.ecommerce.configs.Status;
import com.example.ecommerce.configs.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final Database db;

    @Autowired
    public OrderService(Database db) { this.db = db; }

    public List<Order> getOrders(Integer id) {
        String query = "SELECT * FROM orders WHERE buyer_id = ?";

        return db.fetch(query, new Object[]{id}, new OrderRowMapper());
    }


    public Order getOrderById(Integer id) {
        String query = "SELECT * FROM orders WHERE id=?";

        return db.fetchByOne(query, new Object[]{id}, new OrderRowMapper());
    }

    public int addNewOrder(Order order) {
        String query = "INSERT INTO orders (buyer_id, notes, total, discount, ordered_at, is_paid) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{
                order.getBuyer(),
                order.getNotes(),
                order.getTotal(),
                order.getDiscount(),
                order.getOrderedAt(),
                order.isPaid()
        };

        return db.insert(query, params);
    }

    public int updateOrder(Integer id, Order order) {
        String query = "UPDATE orders SET buyer_id=?, notes=?, total=?, discount=?, is_paid=? WHERE id=?";
        Order orderInDb = db.fetchByIdInTable("orders", id, new OrderRowMapper());

        Utils.updateObjectValues(orderInDb, order);
        Object[] params = new Object[]{
                orderInDb.getBuyer(),
                orderInDb.getNotes(),
                orderInDb.getTotal(),
                orderInDb.getDiscount(),
                orderInDb.isPaid(),
                id
        };

        return db.insert(query, params);
    }

    public int removeOrder(Integer id) { return db.remove("orders", new Object[]{id}); }

    public Status verify(Order order, Integer userId) {
        boolean userExist = db.findOneInTable("users", new Object[]{userId});

        if (!userExist) {
            return Status.NOT_FOUND;
        }

        String currentTime = Utils.createTimeAt();

        order.setBuyer(userId);
        order.setOrderedAt(currentTime);

        boolean stillNull = Utils.nullChecker(order);

        if (stillNull) {
            return Status.VALUES_STILL_NULL;
        }

        stillNull = order.getDetails().getProduct() == null || order.getDetails().getQuantity() == null || order.getDetails().getPrice() == null;

        if (stillNull) {
            return Status.VALUES_STILL_NULL;
        }

        order.setId(db.getMaxIdFromTable("orders"));
        order.getDetails().setOrder(order.getId());
        return Status.OK;
    }
}
