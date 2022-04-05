package com.example.shoppingpay.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingpay.models.CartItem;
import com.example.shoppingpay.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {

    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if(mutableCart.getValue()==null){
            initcart();
        }
        return mutableCart;
    }

    private void initcart() {
        mutableCart.setValue(new ArrayList<CartItem>());
    }

    public boolean addItemToCart (Product product){
        if(mutableCart.getValue()==null){
            initcart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        CartItem cartItem = new CartItem(product,1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        return true;
    }
}
