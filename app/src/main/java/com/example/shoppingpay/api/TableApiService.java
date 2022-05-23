package com.example.shoppingpay.api;

import com.example.shoppingpay.models.table.Table1;
import com.example.shoppingpay.models.table.Table2;
import com.example.shoppingpay.models.table.Table21;
import com.example.shoppingpay.models.table.Table22;
import com.example.shoppingpay.models.table.Table23;
import com.example.shoppingpay.models.table.Table24;
import com.example.shoppingpay.models.table.Table3;
import com.example.shoppingpay.models.table.Table4;
import com.example.shoppingpay.models.table.Table5;
import com.example.shoppingpay.models.table.Table6;
import com.example.shoppingpay.models.table.TableStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TableApiService {

    //Link API: http://192.168.1.23:8000/
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    TableApiService tableApiService  = new Retrofit.Builder()
            .baseUrl("http://192.168.1.23:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TableApiService.class);

    @POST("api/table/1")
    Call<Table1> getApiTable1 ();
    @POST("api/table/2")
    Call<Table2> getApiTable2 ();
    @POST("api/table/3")
    Call<Table3> getApiTable3 ();
    @POST("api/table/4")
    Call<Table4> getApiTable4 ();
    @POST("api/table/5")
    Call<Table5> getApiTable5 ();
    @POST("api/table/6")
    Call<Table6> getApiTable6 ();
    @POST("api/table/7")
    Call<Table21> getApiTable21 ();
    @POST("api/table/8")
    Call<Table22> getApiTable22 ();
    @POST("api/table/9")
    Call<Table23> getApiTable23 ();
    @POST("api/table/10")
    Call<Table24> getApiTable24 ();

    @FormUrlEncoded
    @POST("api/table_edit/{id}")
    Call<TableStatus> updateTable(@Path ("id") int tableId, @Field("status") int status);
}