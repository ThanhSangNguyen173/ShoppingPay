package com.example.shoppingpay.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingpay.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerActivity extends AppCompatActivity {

    DatabaseReference mData;
    EditText edt_key;
    Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_owner);
        mData = FirebaseDatabase.getInstance().getReference();
        edt_key = findViewById(R.id.edt_key);
        btn_reset = findViewById(R.id.btn_reset);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = edt_key.getText().toString();

                if (key.equals("173999")){
                    mData.child("TB1").setValue("t");
                    mData.child("TB2").setValue("t");
                    mData.child("TB3").setValue("t");
                    mData.child("TB4").setValue("t");
                    mData.child("TB5").setValue("t");
                    mData.child("TB6").setValue("t");
                    mData.child("TB21").setValue("t");
                    mData.child("TB22").setValue("t");
                    mData.child("TB23").setValue("t");
                    mData.child("TB24").setValue("t");
                    Toast.makeText(OwnerActivity.this, "Đã reset trạng thái của tất cả các bàn!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(OwnerActivity.this, "Vui lòng nhập đúng keyword!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}