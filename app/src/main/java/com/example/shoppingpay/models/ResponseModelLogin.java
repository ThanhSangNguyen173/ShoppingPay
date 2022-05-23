package com.example.shoppingpay.models;

public class ResponseModelLogin {
    Userlogin user;
    String message;

    public ResponseModelLogin(Userlogin user, String message) {
        this.user = user;
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


    public Userlogin getUser() {
        return user;
    }

    public void setUser(Userlogin user) {
        this.user = user;
    }
}
