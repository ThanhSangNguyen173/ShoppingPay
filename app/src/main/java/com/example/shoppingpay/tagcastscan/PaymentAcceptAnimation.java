package com.example.shoppingpay.tagcastscan;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.RateUsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentAcceptAnimation extends AppCompatActivity {
    TextView animation, txt_status;
    DatabaseReference mData;
    LottieAnimationView lottie;
    ImageView img_thanks;
    String tablenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.payment_accept);

        mData = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        tablenumber = bundle.getString("table");

        setDataTable();
        anhxa();
        animationSetup();
        setHandler();
    }

    @Override
    public void onBackPressed() {
    }

    private void setHandler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PaymentAcceptAnimation.this, "Thank you, hope to see your feedback.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentAcceptAnimation.this, RateUsActivity.class);
                startActivity(intent);
            }
        },5000);
    }

    private void setDataTable() {
        switch (tablenumber){
            case "table1":
                mData.child("TB1").setValue("t");
                break;
            case "table2":
                mData.child("TB2").setValue("t");
                break;
            case "table3":
                mData.child("TB3").setValue("t");
                break;
            case "table4":
                mData.child("TB4").setValue("t");
                break;
            case "table5":
                mData.child("TB5").setValue("t");
                break;
            case "table6":
                mData.child("TB6").setValue("t");
                break;
            case "table21":
                mData.child("TB21").setValue("t");
                break;
            case "table22":
                mData.child("TB22").setValue("t");
                break;
            case "table23":
                mData.child("TB23").setValue("t");
                break;
            case "table24":
                mData.child("TB24").setValue("t");
                break;
        }
    }

    private void animationSetup() {
        animation.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(3000);
    }

    private void anhxa() {
        animation = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);
        txt_status = findViewById(R.id.txt_status);
        img_thanks = findViewById(R.id.img_thanks);
    }

}
