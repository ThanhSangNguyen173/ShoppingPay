package com.example.shoppingpay.views.activity.loginregister;

public class ResponseModelLogin {
    Userlogin userlogin;
    String message;

    public ResponseModelLogin(String message) {
        this.message = message;
    }

    public ResponseModelLogin() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
