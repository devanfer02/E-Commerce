package com.example.ecommerce.product;

public class Product {
    private Integer id;
    private Integer seller;
    private String title;
    private String description;
    private Long price;
    private Integer stock;

    public Product() {
    }

    public Product(Integer id, Integer seller, String title, String description, Long price, Integer stock) {
        this.id = id;
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(Integer seller, String title, String description, Long price, Integer stock) {
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public void update(Product newProduct) {
        if(newProduct.seller != null) {
            this.seller = newProduct.seller;
        }
        if(newProduct.title != null) {
            this.title = newProduct.title;
        }
        if(newProduct.description != null) {
            this.description = newProduct.description;
        }
        if(newProduct.price != null) {
            this.price = newProduct.price;
        }
        if(newProduct.stock != null) {
            this.stock = newProduct.stock;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", seller=" + seller +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", stock=" + stock +
                '}';
    }
}
