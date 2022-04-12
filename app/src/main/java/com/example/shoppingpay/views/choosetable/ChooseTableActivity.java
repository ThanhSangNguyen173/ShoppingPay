package com.example.shoppingpay.views.choosetable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingpay.tagcastscan.MainTagCastActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseTableActivity extends AppCompatActivity {

    DatabaseReference mData;
    String pickserial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();

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
