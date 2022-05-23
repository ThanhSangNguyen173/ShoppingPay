package com.example.shoppingpay.models;

public class ResponseModelLogin {
    Userlogin userlogin;
    String message;

    public ResponseModelLogin(Userlogin userlogin, String message) {
        this.userlogin = userlogin;
        this.message = message;
    }

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

    public Userlogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(Userlogin userlogin) {
        this.userlogin = userlogin;
    }
}
