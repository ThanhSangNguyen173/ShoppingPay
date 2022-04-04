package com.example.shoppingpay.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingpay.models.Product;
import com.example.shoppingpay.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();

    public LiveData<List<Product>> getProducts(){

        return shopRepo.getProducts();
    }
}
