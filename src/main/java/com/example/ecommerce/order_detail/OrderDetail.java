package com.example.ecommerce.order_detail;

public class OrderDetail {
    private Integer order;
    private Integer product;
    private Integer quantity;
    private Double price;

    public OrderDetail() {}
    
    public OrderDetail(Integer order, Integer product, Integer quantity, Double price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
