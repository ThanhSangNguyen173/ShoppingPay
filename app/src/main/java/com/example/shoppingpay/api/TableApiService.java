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

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("api/table/1")
    Call<Table1> getApiTable1 ();
    @GET("api/table/2")
    Call<Table2> getApiTable2 ();
    @GET("api/table/3")
    Call<Table3> getApiTable3 ();
    @GET("api/table/4")
    Call<Table4> getApiTable4 ();
    @GET("api/table/5")
    Call<Table5> getApiTable5 ();
    @GET("api/table/6")
    Call<Table6> getApiTable6 ();
    @GET("api/table/7")
    Call<Table21> getApiTable21 ();
    @GET("api/table/8")
    Call<Table22> getApiTable22 ();
    @GET("api/table/9")
    Call<Table23> getApiTable23 ();
    @GET("api/table/10")
    Call<Table24> getApiTable24 ();

    @FormUrlEncoded
    @PUT("api/table/{id}")
    Call<TableStatus> updateTable(@Path ("id") int tableId, @Field("status") int status);
}