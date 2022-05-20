package com.example.shoppingpay.models;

public class Bill {
    int id;
    int order_id;
    int desk_id;
    int user_id;
    String time_in;
    String time_out;

    public Bill(int id, int order_id, int desk_id, int user_id, String time_in, String time_out) {
        this.id = id;
        this.order_id = order_id;
        this.desk_id = desk_id;
        this.user_id = user_id;
        this.time_in = time_in;
        this.time_out = time_out;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDesk_id() {
        return desk_id;
    }

    public void setDesk_id(int desk_id) {
        this.desk_id = desk_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTime_in() {
        return time_in;
    }

    public void setTime_in(String time_in) {
        this.time_in = time_in;
    }

    public String getTime_out() {
        return time_out;
    }

    public void setTime_out(String time_out) {
        this.time_out = time_out;
    }
}
