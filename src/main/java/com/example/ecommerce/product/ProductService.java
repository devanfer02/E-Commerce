package com.example.ecommerce.product;

import com.example.ecommerce.configs.Database;
import com.example.ecommerce.configs.Status;
import com.example.ecommerce.configs.Type;
import com.example.ecommerce.configs.Utils;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRowMapper;
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

    public List<Product> getProducts(Long min, Long max) {
        String query = "SELECT * FROM products WHERE price >= ? AND price <= ?";
        Object[] params = new Object[]{
                min,
                max
        };

        return db.fetch(query, params, new ProductRowMapper());
    }

    public List<Product> getProducts(Long val, String type) {
        String query;
        Object[] params = new Object[]{
                val
        };
        if (type.equals("min")) {
            query = "SELECT * FROM products WHERE price >= ?";
        } else {
            query = "SELECT * FROM products WHERE price <= ?";
        }
        return db.fetch(query, params, new ProductRowMapper());
    }

    public Product getProductById(Integer id) {
        String query = "SELECT * FROM products WHERE id=?";

        return db.fetchByOne(query, new Object[]{id}, new ProductRowMapper());
    }

    public int addNewProduct(Product product) {
        String query = "INSERT INTO products (seller_id, title, description, price, stock) VALUES (?, ?, ?, ?, ?)";
        Object[] params = new Object[]{
                product.getSeller(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        };

        return db.insert(query, params);
    }

    public int updateProduct(Integer id, Product product){
        String query = "UPDATE products SET seller_id=?, title=?, description=?, price=?, stock=? WHERE id=?";
        Product productInDb = db.fetchByIdInTable("products", id, new ProductRowMapper());

        Utils.updateObjectValues(productInDb, product);

        Object[] params = new Object[]{
                productInDb.getSeller(),
                productInDb.getTitle(),
                productInDb.getDescription(),
                productInDb.getPrice(),
                productInDb.getStock(),
                id
        };

        return db.insert(query, params);
    }

    public int removeProduct(Integer id) {
        return db.remove("products", new Object[]{id});
    }

    public Status verify(Product product, Integer userId) {
        boolean userExist = db.findOneInTable("users", new Object[]{userId});

        if (!userExist) {
            return Status.NOT_FOUND;
        }

        product.setSeller(userId);
        User user = db.fetchByIdInTable("users", product.getSeller(), new UserRowMapper());

        if(user.getType() != Type.SELLER) {
            return Status.DIFF_TYPE;
        }

        boolean stillNull = Utils.nullChecker(product);

        if(stillNull) {
            return Status.VALUES_STILL_NULL;
        }

        product.setId(db.getMaxIdFromTable("products"));
        return Status.OK;
    }

}
