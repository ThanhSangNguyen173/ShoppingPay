package com.example.shoppingpay.repositories;

import android.content.Context;
import android.os.Handler;
import android.text.NoCopySpan;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingpay.api.ApiProductService;
import com.example.shoppingpay.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;
    private Context ShopRepo;
    String name,imgurl, price;
    Boolean isAvailable;

    public LiveData<List<Product>> getProducts(){
        if (mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            getAPIProduct();
        }
        return mutableProductList;
    }

    private void getAPIProduct(){
        ApiProductService.apiproducts.getListProduct().enqueue(new Callback<List<Product>>() {
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

            }
        });

    }

}
