package com.example.ecommerce.order.order_detail;

import com.example.ecommerce.configs.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders/details")
public class OrderDetailController {
    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getDetailsByOrderId(@PathVariable Integer orderId) {
        try {
            OrderDetail orderDetail = orderDetailService.getDetailsByOrderId(orderId);

            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", orderDetail);
        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.BAD_REQUEST, "data not found", null);
        }
    }
}
