package com.example.shoppingpay.repositories;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingpay.api.ApiProductService;
import com.example.shoppingpay.models.Product;
import com.example.shoppingpay.models.ResponseModelLogin;
import com.example.shoppingpay.views.activity.DashboardActivity;
import com.example.shoppingpay.views.activity.loginregister.Controller;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;
    String token_user;

    public LiveData<List<Product>> getProducts(){
        if (mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loginAdmin();
        }
        return mutableProductList;
    }
    private void loginAdmin() {
        String username = "admin";
        String password = "admin";
        Call<ResponseModelLogin> call= Controller
                .getInstance()
                .getapi()
                .verifyuser(username,password);
        call.enqueue(new Callback<ResponseModelLogin>() {
            @Override
            public void onResponse(Call<ResponseModelLogin> call, Response<ResponseModelLogin> response) {
                ResponseModelLogin objLogin = response.body();
                String output = objLogin.getMessage();
                if (output.equals("Success")){
                    token_user      = response.body().getToken();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getAPIProduct();
                        }
                    },1000);
                }
            }
            @Override
            public void onFailure(Call<ResponseModelLogin> call, Throwable t) {
                Log.d("API", t.toString());
            }
        });
    }
    private void getAPIProduct(){
        ApiProductService.apiproducts.getListProduct(token_user).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                if(productList!=null) {
                    List<Product> products = new ArrayList<>();
                    int quantity = productList.size();
                    for(int i = 0; i< quantity; i++){
                        Product product = productList.get(i);
                        products.add(new Product(product.getId(),product.getName(),product.getPrice(),product.isAvailable(),product.getImgUrl()));
                    }
                    mutableProductList.setValue(products);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("API", t.toString());
                getAPIProduct();
            }
        });

    }

}
