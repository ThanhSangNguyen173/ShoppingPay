package com.example.shoppingpay.views.activity.loginregister;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.DashboardActivity;
import com.example.shoppingpay.views.activity.LocationActivity;
import com.example.shoppingpay.views.activity.RateUsActivity;
import com.example.shoppingpay.views.activity.choosetable.ChooseTableActivity;
import com.example.shoppingpay.views.customview.CustomToastNotification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRegisterActivity extends AppCompatActivity {

    Button bt1, bt2,btnthoat;
    EditText tk, mk;
    Button dky2, huydk;
    EditText ho,ten,dob,pass,copass,id;
    CheckBox checkBox;
    RadioButton nam,nu;
    String taikhoan,matkhau,str1,str2;//username,password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.login_page);

        anhxa();
        controlbutton();
    }

    public void showToast(int i) {
        CustomToastNotification customToastNotification = new CustomToastNotification(this);
        switch (i) {
            case 0:
                customToastNotification.setMessage("Vui lòng nhập đầy đủ thông tin và đồng ý điều khoản sử dụng!");
                break;
            case 1:
                customToastNotification.setMessage("Đăng ký thành công!");
                break;
            case 2:
                customToastNotification.setMessage("Welcome back " + str1);
                break;
            case 3:
                customToastNotification.setMessage("Welcome back BOXYZVN");
                break;
            case 4:
                customToastNotification.setMessage("Vui lòng kiểm tra lại ID/Password!");
                break;
            case 5:
                customToastNotification.setMessage("Vui lòng nhập đầy đủ thông tin!");
                break;
            case 6:
                customToastNotification.setMessage("Mật khẩu và xác nhận không trùng khớp!");
                break;
        }
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        if(i == 2 || i == 3){
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        }
        toast.setView(customToastNotification);
        toast.show();
    }

    private void anhxa() {
        bt1 = findViewById(R.id.login);
        bt2 = findViewById(R.id.dky);
        tk = findViewById(R.id.tk);
        mk = findViewById(R.id.mk);
        btnthoat = findViewById(R.id.btnthoat);

        bt1.setOnClickListener(this::OnClick);
        bt2.setOnClickListener(this::OnClick);
    }

    private void OnClick(View view) {
        switch (view.getId()){
            case R.id.login: clickLogin();
            break;
            case  R.id.dky:clickDky();
            break;
        }
    }

    /**
     * Register
     */
    private void clickDky() {

        Intent intent = new Intent(LoginRegisterActivity.this,RegisterActivity.class);
        startActivity(intent);

    }

    /**
     * Login
     */
    private void clickLogin() {



        String username = tk.getText().toString().trim();
        String password = mk.getText().toString().trim();
        if (!username.isEmpty() || !password.isEmpty()) {
           //sendLogin();
            Call<responsemodel> call=controller
                    .getInstance()
                    .getapi()
                    .verifyuser(username,password);
            Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();

            call.enqueue(new Callback<responsemodel>() {
                @Override
                public void onResponse(Call<responsemodel> call, Response<responsemodel> response) {
                    responsemodel obj = response.body();
                    String output = obj.getMessage();
                    if (output.equals("Failed")){
                        tk.setText("");
                        mk.setText("");
                        Toast.makeText(LoginRegisterActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                    if (output.equals("Success")){
                        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("username",tk.getText().toString());
                        editor.putString("password",mk.getText().toString());
                        editor.commit();
                        editor.apply();

                        Intent intentlog = new Intent(LoginRegisterActivity.this, DashboardActivity.class);
                        startActivity(intentlog);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<responsemodel> call, Throwable t) {

                }
            });

        }else {
            showToast(5);
        }

    }

//    private void sendLogin() {
//
//        Call<responsemodel> call=controller
//                .getInstance()
//                .getapi()
//                .verifyuser()
//        Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();
//    }


    private void controlbutton() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginRegisterActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("THOÁT KHỎI TRANG ĐĂNG NHẬP");
                builder.setMessage("Bạn có chắc chắn muốn thoát?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }
}