package com.example.shoppingpay.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.choosetable.ChooseTableActivity;
import com.example.shoppingpay.views.customview.CustomToastNotification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RateUsActivity extends AppCompatActivity {

    ImageView rate_status;
    TextView txt_timein, txt_timeout;
    Button btn_rate,btn_later;
    AppCompatRatingBar rating_bar;
    String timein, timeout;
    DatabaseReference mData;
    Float rating, realtimedb, ratepush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_rate_us);

        mData = FirebaseDatabase.getInstance().getReference();

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

                rating = v;
                setRatingData();
                animateImage(rate_status);
            }
        });
    }

    private void setRatingData() {
        mData.child("Rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue().toString();
                realtimedb = Float.parseFloat(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RateUsActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
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
                if(rating == null){
                    showToast(2);
                }else{
                ratepush = (realtimedb + rating)/2;
                mData.child("Rating").setValue(ratepush);
                startActivity(intent);
                showToast(0);}
                break;
            case R.id.btn_later:
                startActivity(intent);
                showToast(1);
                break;
        }
    }

    public void showToast(int i) {
        CustomToastNotification customToastNotification = new CustomToastNotification(this);
        switch (i) {
            case 0:
                customToastNotification.setMessage("Thanks for your rate. Hope to see you soon.");
                break;
            case 1:
                customToastNotification.setMessage("See you again!");
                break;
            case 2:
                customToastNotification.setMessage("Please give your rating.");
                break;
        }
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(customToastNotification);
        toast.show();
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