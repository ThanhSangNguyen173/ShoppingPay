package com.example.shoppingpay.views.activity.loginregister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {

    @FormUrlEncoded
    @POST("login")
    Call <responsemodel> verifyuser( @Field("username")String username,
                                     @Field("password")String password);

}
