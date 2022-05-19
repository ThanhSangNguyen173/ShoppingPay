package com.example.shoppingpay.views.activity.loginregister;

public class ResponseModelRegister {

    String message;
    String error;

    public ResponseModelRegister(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public ResponseModelRegister() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
