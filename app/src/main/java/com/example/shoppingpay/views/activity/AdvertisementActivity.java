package com.example.shoppingpay.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;


import com.example.shoppingpay.adapters.IntroViewAdapter;
import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.loginregister.ScreenAdvertisement;
import com.example.shoppingpay.views.fragment.ShopFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementActivity extends AppCompatActivity {
    Button btnnext,btnstart;
    private ViewPager screenPager;
    private IntroViewAdapter introViewAdapter;
    TabLayout tabIndicator;
    int a = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_advertisement);
        btnnext = findViewById(R.id.btn_next);
        btnstart = findViewById(R.id.btn_start);
        tabIndicator = findViewById(R.id.tab_indicator);
        List<ScreenAdvertisement> mList = new ArrayList<>();
        mList.add(new ScreenAdvertisement("Fresh Food","Fresh food is food which has not been preserved and has not spoiled yet. For vegetables and fruits, this means that they have been recently harvested and treated properly postharvest; for meat, it has recently been slaughtered and butchered; for fish, it has been recently caught or harvested and kept cold.",R.drawable.img1));
        mList.add(new ScreenAdvertisement("Fast Delivery","Fresh food is food which has not been preserved and has not spoiled yet. For vegetables and fruits, this means that they have been recently harvested and treated properly postharvest; for meat, it has recently been slaughtered and butchered; for fish, it has been recently caught or harvested and kept cold.",R.drawable.img2));
        mList.add(new ScreenAdvertisement("Easy Payment","Fresh food is food which has not been preserved and has not spoiled yet. For vegetables and fruits, this means that they have been recently harvested and treated properly postharvest; for meat, it has recently been slaughtered and butchered; for fish, it has been recently caught or harvested and kept cold.",R.drawable.img3));
        screenPager = findViewById(R.id.screen_viewpager);
        introViewAdapter = new IntroViewAdapter(this,mList);
        screenPager.setAdapter(introViewAdapter);
        tabIndicator.setupWithViewPager(screenPager);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                a = screenPager.getCurrentItem();
                if(a < mList.size()){
                    a++;
                    screenPager.setCurrentItem(a);

                }
                if (a == mList.size()-1){
                    loadLastScreen();
                }

            }
        });
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdvertisementActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void loadLastScreen() {

        btnnext.setVisibility(View.INVISIBLE);
        btnstart.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
    }
}