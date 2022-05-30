package com.example.shoppingpay.models;

public class ResponseModelLogin {
    Userlogin user;
    String message;
    String token;

    public ResponseModelLogin(Userlogin user, String message, String token) {
        this.user = user;
        this.message = message;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

        public Userlogin getUser() {
        return user;
    }

    public void setUser(Userlogin user) {
        this.user = user;
    }


}
