package com.example.shoppingpay.views.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.choosetable.ChooseTableActivity;
import com.example.shoppingpay.views.activity.loginregister.LoginActivity;

public class DashboardActivity extends AppCompatActivity {

    CardView cv_member, cv_table, cv_location, cv_support, cv_contact, cv_owner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activtity_dashboard);

        anhxa();
        clickListener();
    }

    private void clickListener() {
        cv_member.setOnClickListener(this::onClick);
        cv_table.setOnClickListener(this::onClick);
        cv_owner.setOnClickListener(this::onClick);
        cv_location.setOnClickListener(this::onClick);
        cv_support.setOnClickListener(this::onClick);
        cv_contact.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_member:
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
            break;
            case R.id.cv_table:
                Intent intent2 = new Intent(DashboardActivity.this, ChooseTableActivity.class);
                startActivity(intent2);
            break;
            case R.id.cv_owner:
                Intent intent3 = new Intent(DashboardActivity.this, OwnerActivity.class);
                startActivity(intent3);
            break;
            case R.id.cv_location:
                Intent intent4 = new Intent(DashboardActivity.this, LocationActivity.class);
                startActivity(intent4);
            break;
            case R.id.cv_support:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(DashboardActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder2.setTitle("BẠN SẼ CHUYỂN HƯỚNG ỨNG DỤNG?");
                builder2.setMessage("Bạn có chắc chắn muốn duyệt web?");
                builder2.setIcon(android.R.drawable.ic_dialog_alert);
                builder2.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://boxyzvn.com/"));
                        startActivity(browserIntent);
                    }
                });
                builder2.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder2.show();
            break;
            case R.id.cv_contact:
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("BẠN SẼ THỰC HIỆN CUỘC GỌI?");
                builder.setMessage("Bạn có chắc chắn muốn liên lạc?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentcall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0913902365"));
                        startActivity(intentcall);
                    }
                });
                builder.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            break;
        }
    }

    private void anhxa() {
        cv_member = findViewById(R.id.cv_member);
        cv_table = findViewById(R.id.cv_table);
        cv_location = findViewById(R.id.cv_location);
        cv_support = findViewById(R.id.cv_support);
        cv_contact = findViewById(R.id.cv_contact);
        cv_owner = findViewById(R.id.cv_owner);
    }
}
