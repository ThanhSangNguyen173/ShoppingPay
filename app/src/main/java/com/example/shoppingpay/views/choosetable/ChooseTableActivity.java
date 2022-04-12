package com.example.shoppingpay.views.choosetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingpay.R;
import com.example.shoppingpay.tagcastscan.MainTagCastActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseTableActivity extends AppCompatActivity {

    DatabaseReference mData;
    String pickserial;

    TabHost tabHost;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4,imgbtn5,imgbtn6,imgbtn21,imgbtn22,imgbtn23,imgbtn24;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_table);
        mData = FirebaseDatabase.getInstance().getReference();

        anhxa();
        clickListener();

        tabHost.setup();
        TabHost.TabSpec spec1,spec2;

        spec1 = tabHost.newTabSpec("t1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Floor 1");
        tabHost.addTab(spec1);

        spec2 = tabHost.newTabSpec("t2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Floor 2");
        tabHost.addTab(spec2);
    }

    private void clickListener() {
        imgbtn1.setOnClickListener(this::onClick);
        imgbtn2.setOnClickListener(this::onClick);
        imgbtn3.setOnClickListener(this::onClick);
        imgbtn4.setOnClickListener(this::onClick);
        imgbtn5.setOnClickListener(this::onClick);
        imgbtn6.setOnClickListener(this::onClick);
        imgbtn21.setOnClickListener(this::onClick);
        imgbtn22.setOnClickListener(this::onClick);
        imgbtn23.setOnClickListener(this::onClick);
        imgbtn24.setOnClickListener(this::onClick);
    }

    private void anhxa() {
        tabHost = findViewById(R.id.tabhost_table);
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

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_table1:
                mData.child("PickTable").setValue("1");
                GetDataTable();
                break;
            case R.id.btn_table2:
                mData.child("PickTable").setValue("2");
                GetDataTable();
                break;
            case R.id.btn_table3:
                mData.child("PickTable").setValue("JPN-PPER-0292-8289-7478");
                GetDataTable();
                break;
            case R.id.btn_table4:
                mData.child("PickTable").setValue("4");
                GetDataTable();
                break;
            case R.id.btn_table5:
                mData.child("PickTable").setValue("5");
                GetDataTable();
                break;
            case R.id.btn_table6:
                mData.child("PickTable").setValue("6");
                GetDataTable();
                break;
            case R.id.btn_table21:
                mData.child("PickTable").setValue("21");
                GetDataTable();
                break;
            case R.id.btn_table22:
                mData.child("PickTable").setValue("22");
                GetDataTable();
                break;
            case R.id.btn_table23:
                mData.child("PickTable").setValue("JPN-PPER-0292-8289-7478");
                GetDataTable();
                break;
            case R.id.btn_table24:
                mData.child("PickTable").setValue("24");
                GetDataTable();
                break;
        }
    }

    private void GetDataTable() {
        mData.child("PickTable").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pickserial = snapshot.getValue().toString();
                Intent intent = new Intent(ChooseTableActivity.this, MainTagCastActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("seri",pickserial);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
