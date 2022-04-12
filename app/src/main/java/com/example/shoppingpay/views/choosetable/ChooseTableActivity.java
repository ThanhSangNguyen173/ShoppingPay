package com.example.shoppingpay.views.choosetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4,imgbtn5,imgbtn6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_table);

        tabHost = findViewById(R.id.tabhost_table);
        tabHost.setup();
        TabHost.TabSpec spec1,spec2;

        spec1 = tabHost.newTabSpec("t1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Floor 1");
        tabHost.addTab(spec1);

        spec2 = tabHost.newTabSpec("t1");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Floor 2");
        tabHost.addTab(spec2);

        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("PickTable").setValue("1");
        GetDataTable();
        IntentToScan();

    }

    private void GetDataTable() {
        mData.child("PickTable").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pickserial = snapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IntentToScan() {
        Intent intent = new Intent(ChooseTableActivity.this, MainTagCastActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("seri",pickserial);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
