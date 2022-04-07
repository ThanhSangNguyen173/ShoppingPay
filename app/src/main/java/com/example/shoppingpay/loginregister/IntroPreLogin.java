package com.example.shoppingpay.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingpay.R;

public class IntroPreLogin extends AppCompatActivity {
    Animation zoom;
    ImageView img_intro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        img_intro = findViewById(R.id.img_intro);
        zoom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        img_intro = findViewById(R.id.img_intro);
        img_intro.startAnimation(zoom);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intro = new Intent(getApplicationContext(),LoginRegisterActivity.class);
                startActivity(intro);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
            }
        }, 4000);


    }
}