package com.example.shoppingpay.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingpay.R;
import com.example.shoppingpay.adapters.ShopListAdapter;
import com.example.shoppingpay.databinding.FragmentProductDetailBinding;
import com.example.shoppingpay.models.Product;
import com.example.shoppingpay.viewmodels.ShopViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ProductDetailFragment extends Fragment implements ShopListAdapter.ShopInterface {

    FragmentProductDetailBinding fragmentProductDetailBinding;
    ShopViewModel shopViewModel;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater,container,false);
        return fragmentProductDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProductDetailBinding.setShopViewModel(shopViewModel);
    }
    @Override
    public void addItem(Product product) {
    }

    @Override
    public void onItemClick(Product product) {
    }
}