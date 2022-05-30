package com.example.shoppingpay.api;

import com.example.shoppingpay.models.Bill;
import com.example.shoppingpay.models.OrderItems;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderItemsApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    OrderItemsApiService orderItemApiService  = new Retrofit.Builder()
            .baseUrl("http://192.168.1.23:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(OrderItemsApiService.class);

    @FormUrlEncoded
    @POST("api/orderitem/")
    Call<OrderItems> createOrderItems(@Field("token")String token_user,@Field("products_id")int products_id, @Field("quantity")int quantity, @Field("bill_id")int bill_id);
}
