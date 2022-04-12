package com.example.shoppingpay.tagcastscan;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.example.shoppingpay.R;
import com.example.shoppingpay.views.choosetable.ChooseTableActivity;

public class PaymentAcceptAnimation extends AppCompatActivity {
    TextView animation, txt_status;
    LottieAnimationView lottie;
    ImageView img_thanks;
    String tablenumber;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4,imgbtn5,imgbtn6,imgbtn21,imgbtn22,imgbtn23,imgbtn24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.payment_accept);
        Bundle bundle = getIntent().getExtras();
        tablenumber = bundle.getString("table");

        anhxa();

        switch (tablenumber){
            case "ban1": imgbtn1.setEnabled(true);
                break;
            case "ban2": imgbtn2.setEnabled(true);
                break;
            case "ban3": imgbtn3.setEnabled(true);
                break;
            case "ban4": imgbtn4.setEnabled(true);
                break;
            case "ban5": imgbtn5.setEnabled(true);
                break;
            case "ban6": imgbtn6.setEnabled(true);
                break;
            case "ban21": imgbtn21.setEnabled(true);
                break;
            case "ban22": imgbtn22.setEnabled(true);
                break;
            case "ban23": imgbtn23.setEnabled(true);
                break;
            case "ban24": imgbtn24.setEnabled(true);
                break;
        }

        animation = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);
        txt_status = findViewById(R.id.txt_status);
        img_thanks = findViewById(R.id.img_thanks);

        animation.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(3000);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PaymentAcceptAnimation.this, "Thanks, see you again!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentAcceptAnimation.this, ChooseTableActivity.class);
                startActivity(intent);
            }
        },5000);

    }

    private void anhxa() {
        imgbtn1 = findViewById(R.id.btn_table1);
        imgbtn2 = findViewById(R.id.btn_table2);
        imgbtn3 = findViewById(R.id.btn_table3);
        imgbtn4 = findViewById(R.id.btn_table4);
        imgbtn5 = findViewById(R.id.btn_table5);
        imgbtn6 = findViewById(R.id.btn_table6);
        imgbtn21 = findViewById(R.id.btn_table21);
        imgbtn22 = findViewById(R.id.btn_table22);
        imgbtn23 = findViewById(R.id.btn_table23);
        imgbtn24 = findViewById(R.id.btn_table24);
    }
}
