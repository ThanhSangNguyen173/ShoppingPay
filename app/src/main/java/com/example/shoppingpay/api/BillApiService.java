package com.example.shoppingpay.api;

import com.example.shoppingpay.models.Bill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BillApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    BillApiService billApiService  = new Retrofit.Builder()
            .baseUrl("http://192.168.1.23:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BillApiService.class);

    @FormUrlEncoded
    @POST("api/bill/")
    Call<Bill> creatNewBill(@Field("order_id")int order_id,@Field("desk_id")int desk_id,@Field("user_id")int user_id, @Field("time_in")String time_in,@Field("time_out")String time_out);
}
