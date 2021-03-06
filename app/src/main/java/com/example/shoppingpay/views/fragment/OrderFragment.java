package com.example.shoppingpay.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingpay.R;
import com.example.shoppingpay.databinding.FragmentOrderBinding;
import com.example.shoppingpay.tagcastscan.MainPaymentActivity;
import com.example.shoppingpay.viewmodels.ShopViewModel;
import com.example.shoppingpay.views.activity.MainShoppingActivity;

public class OrderFragment extends Fragment {

    NavController navController;
    FragmentOrderBinding fragmentOrderBinding;
    ShopViewModel shopViewModel;
    String serial, tablenumber, timein, token_user;
    int bill_id;


    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentOrderBinding = FragmentOrderBinding.inflate(inflater,container,false);
        return fragmentOrderBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainShoppingActivity activity = (MainShoppingActivity) getActivity();
        serial = activity.getSerial();
        tablenumber = activity.getTableNumer();
        timein = activity.getTimeIn();
        bill_id = activity.getBillID();
        token_user = activity.getTokenUser();

        navController = Navigation.findNavController(view);
        Context context = getActivity().getApplicationContext();
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentOrderBinding.continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_shopFragment);
            }
        });
        fragmentOrderBinding.paymentTagCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainPaymentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("seri",serial);
                bundle.putString("table",tablenumber);
                bundle.putString("timein",timein);
                bundle.putString("token_user",token_user);
                bundle.putInt("bill_id", bill_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}