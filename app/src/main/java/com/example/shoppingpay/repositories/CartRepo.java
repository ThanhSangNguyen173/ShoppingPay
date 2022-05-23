package com.example.shoppingpay.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingpay.api.OrderItemsApiService;
import com.example.shoppingpay.models.Bill;
import com.example.shoppingpay.models.CartItem;
import com.example.shoppingpay.models.OrderItems;
import com.example.shoppingpay.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepo {

    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if(mutableCart.getValue()==null){
            initcart();
        }
        return mutableCart;
    }

    public void initcart() {
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }

    public boolean addItemToCart (Product product){
        if(mutableCart.getValue()==null){
            initcart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for (CartItem cartItem: cartItemList){
            if(cartItem.getProduct().getId().equals(product.getId())){
                if(cartItem.getQuantity()==5){
                    return false;
                }

                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItemList.set(index,cartItem);

                mutableCart.setValue(cartItemList);
                calculateCartTotal();
                return true;

            }
        }
        CartItem cartItem = new CartItem(product,1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItem cartItem){
        if(mutableCart.getValue()==null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    public void changeQuantity(CartItem cartItem,int quantity){
        if(mutableCart.getValue()==null) return;
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        CartItem updatedItem = new CartItem(cartItem.getProduct(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem),updatedItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    private void calculateCartTotal(){
        if(mutableCart.getValue()==null) return;
        double total = 0.0;
        List<CartItem> cartItemList = mutableCart.getValue();
        for (CartItem cartItem:cartItemList){
            total += Double.parseDouble(cartItem.getProduct().getPrice())*cartItem.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Double> getTotalPrice(){
        if(mutableTotalPrice.getValue()==null){
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }

    public void callApiCreateOrderItems() {
        if (mutableCart.getValue() == null) return;
        List<CartItem> cartItemList = mutableCart.getValue();
        int limit = cartItemList.size();
        for (int i = 0; i < limit; i++) {
            CartItem cartItem = cartItemList.get(i);
            int id = Integer.parseInt(cartItem.getProduct().getId());
            int quantity = cartItem.getQuantity();
            OrderItemsApiService.orderItemApiService.createOrderItems(id, quantity).enqueue(new Callback<OrderItems>() {
                @Override
                public void onResponse(Call<OrderItems> call, Response<OrderItems> response) {
                    Log.d("API", response.body().toString());
                }

                @Override
                public void onFailure(Call<OrderItems> call, Throwable t) {
                    Log.d("API", t.toString());
                }
            });
        }
    }
}


