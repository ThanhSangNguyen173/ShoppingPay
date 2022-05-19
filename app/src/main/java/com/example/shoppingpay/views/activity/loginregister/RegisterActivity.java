package com.example.shoppingpay.views.activity.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shoppingpay.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtfull_name, edtusername,edtpassword,edtemail,edtDOB,edtCfpassword;
    Button btnResigen, btnhuy;
    String  full_name, username,password,email,DOB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhXa();

        btnResigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void anhXa() {
        edtfull_name = findViewById(R.id.editTextFullname);
        edtDOB=findViewById(R.id.editTextDOB);
        edtemail=findViewById(R.id.editTextEmail);
        edtusername=findViewById(R.id.editTextUsername);
        edtpassword=findViewById(R.id.editTextPass);
        edtpassword=findViewById(R.id.editTextCofPass);

        btnResigen=findViewById(R.id.btn_register);
        btnhuy=findViewById(R.id.btnhuy);

    }
}