package com.example.shoppingpay.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.choosetable.ChooseTableActivity;
import com.example.shoppingpay.views.loginregister.LoginRegisterActivity;

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
                Intent intent = new Intent(DashboardActivity.this, LoginRegisterActivity.class);
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
                Toast.makeText(this, "Hihi bị lừa.", Toast.LENGTH_SHORT).show();
            break;
            case R.id.cv_support:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://boxyzvn.com/"));
                startActivity(browserIntent);
            break;
            case R.id.cv_contact:
                Intent intentcall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0913902365"));
                startActivity(intentcall);
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
