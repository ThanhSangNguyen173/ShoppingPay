package com.example.shoppingpay.views.activity.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingpay.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResginsterssActivity extends AppCompatActivity {

    EditText edtfull_name, edtusername,edtpassword,edtemail,edtDOB,edtCfpassword;
    Button btnResigen, btnhuy;
    String  full_name, username,password,email,DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resginsterss);

        anhXa();

        btnResigen.setOnClickListener(this::OnClick);
        btnhuy.setOnClickListener(this::OnClick);
    }

    private void OnClick(View view) {

        switch (view.getId()){
            case R.id.btn_register: clickRegister();
                break;
            case  R.id.btn_huy:clickHuy();
                break;
        }

    }

    private void clickHuy() {
        Intent intentout = new Intent(ResginsterssActivity.this,LoginRegisterActivity.class);
        startActivity(intentout);
    }

    private void clickRegister() {
        Toast.makeText(this, "send resigen", Toast.LENGTH_SHORT).show();
        String full_name=edtfull_name.getText().toString();
        String DOB=edtDOB.getText().toString();
        String email=edtemail.getText().toString();
        String username=edtusername.getText().toString();
        String password=edtpassword.getText().toString();
        String cofpassword=edtCfpassword.getText().toString();

        if (username.isEmpty()
                ||full_name.isEmpty()
                ||DOB.isEmpty()
                ||email.isEmpty()
                ||password.isEmpty()){
            Toast.makeText(this, "Please enter enough information", Toast.LENGTH_SHORT).show();
        
        }else if(!password.equals(cofpassword)){
            Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show();
        }else {
            Call<responseRegisterModel> call = controller
                                                .getInstance()
                                                .getapi()
                                                .register(full_name,DOB,email,username,password);

            call.enqueue(new Callback<responseRegisterModel>() {
                @Override
                public void onResponse(Call<responseRegisterModel> call, Response<responseRegisterModel> response) {
                    responseRegisterModel objreg = response.body();
                    String outputreg = objreg.getMessage();
                    if (outputreg.equals("Resigen Success"))
                    {
                        Toast.makeText(ResginsterssActivity.this, "Resigen Success", Toast.LENGTH_SHORT).show();
                        Intent intentlog = new Intent(ResginsterssActivity.this,LoginRegisterActivity.class);
                        startActivity(intentlog);
                    }
                }

                @Override
                public void onFailure(Call<responseRegisterModel> call, Throwable t) {
                    Toast.makeText(ResginsterssActivity.this, "have this worong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void anhXa() {
        edtfull_name = findViewById(R.id.editTextFullname);
        edtDOB=findViewById(R.id.editTextDOB);
        edtemail=findViewById(R.id.editTextEmail);
        edtusername=findViewById(R.id.editTextUsername);
        edtpassword=findViewById(R.id.editTextPass);
        edtCfpassword=findViewById(R.id.editTextCofPass);

        btnResigen=findViewById(R.id.btn_register);
        btnhuy=findViewById(R.id.btn_huy);

    }
}