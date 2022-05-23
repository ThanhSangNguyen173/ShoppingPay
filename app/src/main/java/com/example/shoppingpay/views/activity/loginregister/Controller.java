package com.example.shoppingpay.views.activity.loginregister;

import com.example.shoppingpay.api.ApiServe;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    private static final String url ="http://192.168.1.15:8000/api/";
    private static Controller clienobject;
    private static Retrofit retrofit;

        Controller(){
            retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }

        public static synchronized Controller getInstance(){
            if(clienobject==null)
                clienobject=new Controller();
            return clienobject;
        }

        ApiServe getapi(){
            return retrofit.create(ApiServe.class);
        }
}
