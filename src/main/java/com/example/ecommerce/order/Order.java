package com.example.ecommerce.order;

public class Order {
    private Integer id;
    private Integer buyer;
    private Integer total;
    private Double discount;
    private String notes;
    private String orderedAt;
    private boolean isPaid;

    public Order() {}

    public Order(Integer id, Integer buyer, Integer total, Double discount, String notes, String orderedAt, boolean isPaid) {
        this.id = id;
        this.buyer = buyer;
        this.total = total;
        this.discount = discount;
        this.notes = notes;
        this.orderedAt = orderedAt;
        this.isPaid = isPaid;
    }

    public Order(Integer buyer, Integer total, Double discount, String notes, String orderedAt, boolean isPaid) {
        this.buyer = buyer;
        this.total = total;
        this.discount = discount;
        this.notes = notes;
        this.orderedAt = orderedAt;
        this.isPaid = isPaid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuyer() {
        return buyer;
    }

    public void setBuyer(Integer buyer) {
        this.buyer = buyer;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(String orderedAt) {
        this.orderedAt = orderedAt;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", total=" + total +
                ", discount=" + discount +
                ", notes='" + notes + '\'' +
                ", orderedAt='" + orderedAt + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}
