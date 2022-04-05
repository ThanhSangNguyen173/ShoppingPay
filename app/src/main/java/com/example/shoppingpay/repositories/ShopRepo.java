package com.example.shoppingpay.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingpay.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts(){
        if (mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(),"Beef Steak","27",true, "https://res.klook.com/image/upload/c_fill,w_800,h_533/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/zyulungyz94xotkznw79.webp"));
        productList.add(new Product(UUID.randomUUID().toString(),"French Fries","9",true, "https://www.thespruceeats.com/thmb/IHKuXcx3uUI1IWkM_cnnQdFH-zQ=/3485x2323/filters:fill(auto,1)/how-to-make-homemade-french-fries-2215971-hero-01-02f62a016f3e4aa4b41d0c27539885c3.jpg"));

        mutableProductList.setValue(productList);
    }
}
