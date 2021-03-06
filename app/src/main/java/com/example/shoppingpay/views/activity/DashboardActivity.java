package com.example.shoppingpay.views.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.shoppingpay.R;
import com.example.shoppingpay.models.ResponseModelLogin;
import com.example.shoppingpay.views.activity.choosetable.ChooseTableActivity;
import com.example.shoppingpay.views.activity.loginregister.Controller;
import com.example.shoppingpay.views.activity.loginregister.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    CardView cv_member, cv_table, cv_location, cv_support, cv_contact, cv_owner;
    int user_id;
    String token_user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activtity_dashboard);

        anhxa();
        clickListener();
        loginAdmin();
    }

    private void loginAdmin() {

        String username = "admin";
        String password = "admin";
        Call<ResponseModelLogin> call= Controller
                    .getInstance()
                    .getapi()
                    .verifyuser(username,password);
        call.enqueue(new Callback<ResponseModelLogin>() {
                @Override
                public void onResponse(Call<ResponseModelLogin> call, Response<ResponseModelLogin> response) {
                    ResponseModelLogin objLogin = response.body();
                    String output = objLogin.getMessage();
                    if (output.equals("Success")){
                        user_id         = response.body().getUser().getId();
                        token_user      = response.body().getToken();
                    }
                }
                @Override
                public void onFailure(Call<ResponseModelLogin> call, Throwable t) {
                    Toast.makeText(DashboardActivity.this, "Call API failed", Toast.LENGTH_SHORT).show();
                }
        });
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
                Bundle bundle = new Bundle();
                bundle.putInt("user_id",user_id);
                bundle.putString("token_user", token_user);
                intent2.putExtras(bundle);
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
                builder2.setTitle("B???N S??? CHUY???N H?????NG ???NG D???NG?");
                builder2.setMessage("B???n c?? ch???c ch???n mu???n duy???t web?");
                builder2.setIcon(android.R.drawable.ic_dialog_alert);
                builder2.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://boxyzvn.com/"));
                        startActivity(browserIntent);
                    }
                });
                builder2.setNegativeButton("KH??NG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder2.show();
            break;
            case R.id.cv_contact:
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("B???N S??? TH???C HI???N CU???C G???I?");
                builder.setMessage("B???n c?? ch???c ch???n mu???n li??n l???c?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intentcall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0913902365"));
                        startActivity(intentcall);
                    }
                });
                builder.setNegativeButton("KH??NG", new DialogInterface.OnClickListener() {
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
