package com.example.shoppingpay.models;

public class Userlogin {

    int id;
    String full_name;
    String DOB;
    String username;
    String email;

    public Userlogin() {
    }

    public Userlogin(int id, String full_name, String DOB, String username, String email) {
        this.id = id;
        this.full_name = full_name;
        this.DOB = DOB;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
