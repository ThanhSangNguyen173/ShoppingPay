package com.example.shoppingpay.serve;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStrorage {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public LocalStrorage(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("STROAGE_LOGIN_API",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

    }
}
