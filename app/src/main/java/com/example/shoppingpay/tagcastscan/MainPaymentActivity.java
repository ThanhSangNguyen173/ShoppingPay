package com.example.shoppingpay.tagcastscan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.shoppingpay.R;

public class MainPaymentActivity extends AppCompatActivity {

    Button btn_scan;
    String serial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_main);
        Bundle bundle = getIntent().getExtras();
        serial = bundle.getString("seri");
        btn_scan = findViewById(R.id.btn_scan2);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPaymentActivity.this, serial, Toast.LENGTH_SHORT).show();
            }
        });

    }
}