package com.example.ecommerce.product;

import com.example.ecommerce.configs.Response;
import com.example.ecommerce.configs.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getProducts
            (@RequestParam(value = "min", required = false) Long min,
             @RequestParam(value = "max", required = false) Long max) {
        try {
            List<Product> data = null;

            if (min != null && max != null) {
                data = productService.getProducts(min, max);
            } else if(min != null) {
                data = productService.getProducts(min, "min");
            } else if(max != null) {
                data = productService.getProducts(max, "max");
            } else {
                data = productService.getProducts();
            }

            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", data);
        } catch (EmptyResultDataAccessException exception ) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer id) {
        try {
            Product data = productService.getProductById(id);

            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", data);
        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> postProduct(@RequestBody Product product, @PathVariable Integer userId) {
        try {
            Status request = productService.verify(product, userId);

            if (request == Status.NOT_FOUND) {
                return Response.generateResponse(HttpStatus.NOT_FOUND, "seller not found", null);
            }

            if (request == Status.VALUES_STILL_NULL) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "request values still null", null);
            }

            if (request == Status.DIFF_TYPE) {
                return Response.generateResponse(HttpStatus.CONFLICT, "user is not a seller", null);
            }

            int rowsAffected = productService.addNewProduct(product);

            if (rowsAffected == 0) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to add data", null);
            }

            return Response.generateResponse(HttpStatus.CREATED, "successfully add new data", product);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchProduct(@PathVariable Integer id, @RequestBody Product product) {
        try {
            int rowsAffected = productService.updateProduct(id, product);

            if(rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to update data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully update data", null);

        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        try {
            int rowsAffected = productService.removeProduct(id);

            if(rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.NOT_FOUND, "failed to delete data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully delete data", null);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }
}
