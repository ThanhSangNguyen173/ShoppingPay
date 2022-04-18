package com.example.shoppingpay.views.activity.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.AdvertisementActivity;

public class IntroPreLogin extends AppCompatActivity {
    ImageView logo,appName,splashImg;
    LottieAnimationView lottieAnimationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);
        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.textlogin);
        lottieAnimationView = findViewById(R.id.lottie);
        splashImg = findViewById(R.id.imgIntro);

        splashImg.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);


        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intro = new Intent(getApplicationContext(), AdvertisementActivity.class);
                startActivity(intro);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
            }
        }, 4000);

    }
}