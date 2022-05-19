package com.example.shoppingpay.views.activity.loginregister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServe {

    @FormUrlEncoded
    @POST("login")
    Call <ResponseModelLogin> verifyuser(@Field("username")String username,
                                         @Field("password")String password);

    @FormUrlEncoded
    @POST("users")
    Call <ResponseModelRegister> register (@Field("full_name")String full_name,
                                           @Field("DOB")String DOB,
                                           @Field("email")String email,
                                           @Field("username")String username,
                                           @Field("password")String password);

}
