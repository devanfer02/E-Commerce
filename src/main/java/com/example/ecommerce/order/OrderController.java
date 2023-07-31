package com.example.ecommerce.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.configs.Response;
import com.example.ecommerce.configs.Status;

@RestController
@RequestMapping(path="/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping
    public ResponseEntity<Object> getOrders(@RequestParam(value="userId", required=true) Integer userId) {
        try {
            if (userId == null) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "user id not provided in query", null);
            }
            List<Order> orders = orderService.getOrders(userId);

            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", orders);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Integer id) {
        try {
            Order order = orderService.getOrderById(id);

            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", order);
        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
        
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> postOrder(@PathVariable Integer userId, @RequestBody Order order) {
        try {
            Status request = orderService.verify(order, userId);
            
            if (request == Status.NOT_FOUND) {
                return Response.generateResponse(HttpStatus.NOT_FOUND, "user not found", null);
            }

            if (request == Status.VALUES_STILL_NULL) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "request values still null", null);
            }

            int rowsAffected = orderService.addNewOrder(order);

            if (rowsAffected == 0) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to add data", null);
            }

            return Response.generateResponse(HttpStatus.CREATED, "successfully add data", order);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchOrder(@PathVariable Integer id, @RequestBody Order order) {
        try {
            int rowsAffected = orderService.updateOrder(id, order);

            if (rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to update data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully update data", null);
        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Integer id) {
        try {
            int rowsAffected = orderService.removeOrder(id);

            if (rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.NOT_FOUND, "failed to delete data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully delete data", null);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }

}
