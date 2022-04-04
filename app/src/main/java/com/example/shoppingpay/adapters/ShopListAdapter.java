package com.example.shoppingpay.adapters;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingpay.databinding.ShopItemBinding;
import com.example.shoppingpay.models.Product;

public class ShopListAdapter extends ListAdapter<Product, ShopListAdapter.ShopViewHolder> {

    public ShopListAdapter() {
        super(Product.itemCallback);
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShopItemBinding shopItemBinding = ShopItemBinding.inflate(layoutInflater,parent,false);
        return new ShopViewHolder(shopItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Product product = getItem(position);
        holder.shopItemBinding.setProduct(product);

    }

    class ShopViewHolder extends RecyclerView.ViewHolder{

        ShopItemBinding shopItemBinding;

        public ShopViewHolder(ShopItemBinding binding) {
            super(binding.getRoot());
            this.shopItemBinding=binding;
        }
    }

    public interface ShopInterface {
        void addItem(Product product);
        void onItemClick(Product product);
    }
}
