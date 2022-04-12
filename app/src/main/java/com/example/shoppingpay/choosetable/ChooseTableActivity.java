package com.example.shoppingpay.choosetable;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("PickTable").setValue("1");
        GetDataTable();


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
}
