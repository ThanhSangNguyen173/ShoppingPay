package com.example.shoppingpay.api;

import com.example.shoppingpay.models.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public interface ApiProductService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiProductService apiproducts = new Retrofit.Builder()
            .baseUrl("http://192.168.1.23:8000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiProductService.class);


    @POST("products_list")
    Call<List<Product>> getListProduct();
}
