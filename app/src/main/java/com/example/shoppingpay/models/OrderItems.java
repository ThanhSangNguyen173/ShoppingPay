package com.example.shoppingpay.models;

public class OrderItems {
    int id;
    int bill_id;
    int products_id;
    int quantity;
    int total;

    public OrderItems(int id, int bill_id, int products_id, int quantity, int total) {
        this.id = id;
        this.bill_id = bill_id;
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

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
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
