package com.example.shoppingpay.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingpay.databinding.CartItemBinding;
import com.example.shoppingpay.models.CartItem;

import java.util.List;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.CartVH> {

    private CartInterface cartInterface;
    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartItemBinding cartItemBinding = CartItemBinding.inflate(layoutInflater,parent, false);
        return new CartVH(cartItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        holder.cartItemBinding.setCartItem(getItem(position));
        holder.cartItemBinding.executePendingBindings();
    }

    class CartVH extends RecyclerView.ViewHolder {
        CartItemBinding cartItemBinding;
        public CartVH(@NonNull CartItemBinding cartItemBinding) {
            super(cartItemBinding.getRoot());
            this.cartItemBinding = cartItemBinding;

            cartItemBinding.deleteProductButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartInterface.delecteItem(getItem(getAdapterPosition()));
                }
            });

            cartItemBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int quantity = i+1;
                    if(quantity==getItem(getAdapterPosition()).getQuantity()){
                        return;
                    }
                    cartInterface.changeQuantity(getItem(getAdapterPosition()),quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    public interface CartInterface {
        void delecteItem (CartItem cartItem);
        void changeQuantity(CartItem cartItem, int quantity);
    }
}
