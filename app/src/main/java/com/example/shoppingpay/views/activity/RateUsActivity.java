package com.example.shoppingpay.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingpay.R;

public class RateUsActivity extends AppCompatActivity {

    ImageView rate_status;
    TextView txt_timein, txt_timeout;
    Button btn_rate,btn_later;
    AppCompatRatingBar rating_bar;
    String timein, timeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_rate_us);

        Bundle bundle = getIntent().getExtras();
        timein = bundle.getString("timein");
        timeout = bundle.getString("timeout");

        anhxa();
        clicklistener();
        ratingstatus();

    }

    @Override
    public void onBackPressed() {
    }

    private void ratingstatus() {
        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if(v <= 1){
                    rate_status.setBackground(getDrawable(R.drawable.one_star));
                }
                else if(v <= 2){
                    rate_status.setBackground(getDrawable(R.drawable.two_star));
                }
                else if(v <= 3){
                    rate_status.setBackground(getDrawable(R.drawable.three_star));
                }
                else if(v <= 4){
                    rate_status.setBackground(getDrawable(R.drawable.four_star));
                }
                else if(v <= 5){
                    rate_status.setBackground(getDrawable(R.drawable.five_star));
                }

                animateImage(rate_status);
            }
        });
    }

    private void animateImage(ImageView rate_status){

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        rate_status.startAnimation(scaleAnimation);
    }

    private void clicklistener() {
        btn_rate.setOnClickListener(this::OnClick);
        btn_later.setOnClickListener(this::OnClick);
    }

    private void OnClick(View view) {
        Intent intent = new Intent(RateUsActivity.this,DashboardActivity.class);
        switch (view.getId()){
            case R.id.btn_rate:
                startActivity(intent);
                Toast.makeText(this, "Thanks for your rate. Hope to see you soon.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_later:
                startActivity(intent);
                Toast.makeText(this, "See you agian!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void anhxa() {
        rate_status = findViewById(R.id.rate_status);
        btn_later = findViewById(R.id.btn_later);
        btn_rate = findViewById(R.id.btn_rate);
        rating_bar = findViewById(R.id.rating_bar);
        txt_timein = findViewById(R.id.txt_timein);
        txt_timeout = findViewById(R.id.txt_timeout);

        txt_timein.setText(timein);
        txt_timeout.setText(timeout);
    }
}