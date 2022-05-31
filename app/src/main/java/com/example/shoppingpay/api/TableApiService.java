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

    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table1> getApiTable1 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table2> getApiTable2 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table3> getApiTable3 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table4> getApiTable4 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table5> getApiTable5 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table6> getApiTable6 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table21> getApiTable21 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table22> getApiTable22 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table23> getApiTable23 (@Field("token")String token_user, @Field("id_request") int tableId);
    @FormUrlEncoded
    @POST("api/table_id/")
    Call<Table24> getApiTable24 (@Field("token")String token_user, @Field("id_request") int tableId);

    @FormUrlEncoded
    @POST("api/table_edit/")
    Call<TableStatus> updateTable(@Field("id_request") int tableId,@Field("token")String token_user, @Field("status") int status);
}