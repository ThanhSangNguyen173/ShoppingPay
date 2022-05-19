package com.example.shoppingpay.views.activity.loginregister;

public class responsemodel {
    Userlogin userlogin;
    String message;

    public responsemodel(String message) {
        this.message = message;
    }

    public responsemodel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
