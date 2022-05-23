package com.example.shoppingpay.models;

public class OrderItems {
    int id;
    int products_id;
    int quantity;
    int total;

    public OrderItems(int id, int products_id, int quantity, int total) {
        this.id = id;
        this.products_id = products_id;
        this.quantity = quantity;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
