package com.example.ecommerce.product;

import com.example.ecommerce.configs.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Object> getProducts() {
        try {
            List<Product> data = productService.getProducts();
            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", data);
        } catch (EmptyResultDataAccessException exception ) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }
}
