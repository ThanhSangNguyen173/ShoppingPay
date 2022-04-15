package com.example.shoppingpay.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.shoppingpay.adapters.IntroViewAdapter;
import com.example.shoppingpay.R;
import com.example.shoppingpay.adapters.ShopListAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class advertisementActivity extends AppCompatActivity {
    Button btnnext;
    private ViewPager screenPager;
    private IntroViewAdapter introViewAdapter;
    TabLayout tabIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_advertisement);
        btnnext = findViewById(R.id.btn_next);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(advertisementActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });
        tabIndicator = findViewById(R.id.tab_indicator);
        List<ScreenAdvertisement> mList = new ArrayList<>();
        mList.add(new ScreenAdvertisement("Fresh Food","Fresh food is food which has not been preserved and has not spoiled yet. For vegetables and fruits, this means that they have been recently harvested and treated properly postharvest; for meat, it has recently been slaughtered and butchered; for fish, it has been recently caught or harvested and kept cold.",R.drawable.img1));
        mList.add(new ScreenAdvertisement("Fast Delivery","Fresh food is food which has not been preserved and has not spoiled yet. For vegetables and fruits, this means that they have been recently harvested and treated properly postharvest; for meat, it has recently been slaughtered and butchered; for fish, it has been recently caught or harvested and kept cold.",R.drawable.img2));
        mList.add(new ScreenAdvertisement("Easy Payment","Fresh food is food which has not been preserved and has not spoiled yet. For vegetables and fruits, this means that they have been recently harvested and treated properly postharvest; for meat, it has recently been slaughtered and butchered; for fish, it has been recently caught or harvested and kept cold.",R.drawable.img3));
        screenPager = findViewById(R.id.screen_viewpager);
        introViewAdapter = new IntroViewAdapter(this,mList);
        screenPager.setAdapter(introViewAdapter);
        tabIndicator.setupWithViewPager(screenPager);
    }
}