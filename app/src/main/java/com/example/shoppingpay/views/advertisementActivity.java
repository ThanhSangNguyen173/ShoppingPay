package com.example.shoppingpay.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.shoppingpay.adapters.IntroViewAdapter;
import com.example.shoppingpay.R;
import com.example.shoppingpay.adapters.ShopListAdapter;

import java.util.ArrayList;
import java.util.List;

public class advertisementActivity extends AppCompatActivity {

    private ViewPager screenPager;
    private IntroViewAdapter introViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        List<ScreenAdvertisement> mList = new ArrayList<>();
        mList.add(new ScreenAdvertisement("Fresh Food","",R.drawable.img1));
        mList.add(new ScreenAdvertisement("Fast Delivery","",R.drawable.img2));
        mList.add(new ScreenAdvertisement("Easy Payment","",R.drawable.img3));


        screenPager = findViewById(R.id.screen_viewpager);
        introViewAdapter = new IntroViewAdapter(this,mList);
        screenPager.setAdapter(introViewAdapter);



    }
}