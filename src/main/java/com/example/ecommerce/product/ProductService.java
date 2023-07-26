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

    public int addNewProduct(Product product) {
        boolean exist = db.findOneInTable("users", new Object[]{product.getSeller()});

        if(!exist) {
            return -1;
        }

        String query = "INSERT INTO products (seller, title, description, price stock) VALUES (?, ?, ?, ?, ?)";
        Object[] params = new Object[]{
                product.getSeller(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        };
        int rowsAffected = db.insert(query, params);

        return rowsAffected;
    }

    public int updateProduct(Integer id, Product product){
        String query = "UPDATE products SET seller=?, title=?, description=?, price=?, stock=? WHERE id=?";
        Product productInDb = db.fetchByIdInTable("products", id, new ProductRowMapper());

        productInDb.update(product);

        Object[] params = new Object[]{
                productInDb.getSeller(),
                productInDb.getTitle(),
                productInDb.getDescription(),
                productInDb.getPrice(),
                productInDb.getStock(),
                id
        };
        int rowsAffected = db.insert(query, params);
        return rowsAffected;
    }

    public int removeProduct(Integer id) {
        return db.remove("products", new Object[]{id});
    }
}
