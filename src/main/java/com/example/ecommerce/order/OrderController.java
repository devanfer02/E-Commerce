package com.example.ecommerce.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) { this.orderService = orderService; }
    @GetMapping
    public ResponseEntity<Object> getOrders(@RequestParam(value="user", required=false) Integer userId) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Integer id) {
        return null;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> postOrder(@PathVariable Integer userId, @RequestBody Order order) {
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchOrder(@PathVariable Integer id, @RequestBody Order order) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Integer id) {
        return null;
    }

}
