package com.example.shoppingpay.tagcastscan;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.example.shoppingpay.R;

public class PaymentAcceptAnimation extends AppCompatActivity {
    TextView animation, txt_status;
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_accept);

        animation = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);
        txt_status = findViewById(R.id.txt_status);

        animation.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(3000);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txt_status.setText("thank you for your purchase");
            }
        },5000);

    }
}
