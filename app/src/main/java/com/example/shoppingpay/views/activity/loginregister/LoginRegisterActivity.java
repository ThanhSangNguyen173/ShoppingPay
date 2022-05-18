package com.example.shoppingpay.views.activity.loginregister;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.shoppingpay.views.activity.choosetable.ChooseTableActivity;
import com.example.shoppingpay.views.customview.CustomToastNotification;

public class LoginRegisterActivity extends AppCompatActivity {

    Button bt1, bt2,btnthoat;
    EditText tk, mk;
    Button dky2, huydk;
    EditText ho,ten,dob,pass,copass,id;
    CheckBox checkBox;
    RadioButton nam,nu;
    String taikhoan,matkhau,str1,str2,username,password;

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
        Dialog dialog = new Dialog(LoginRegisterActivity.this);
        dialog.setTitle("ĐĂNG KÝ TÀI KHOẢN");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dky_page);
        dky2 = dialog.findViewById(R.id.dky2);
        ho = dialog.findViewById(R.id.editTextHo);
        ten = dialog.findViewById(R.id.editTextTen);
        dob = dialog.findViewById(R.id.editTextDOB);
        pass = dialog.findViewById(R.id.editTextPass);
        copass = dialog.findViewById(R.id.editTextCoPass);
        checkBox = dialog.findViewById(R.id.checkBox);
        nam = dialog.findViewById(R.id.nam);
        nu = dialog.findViewById(R.id.nu);
        id = dialog.findViewById(R.id.editTextID);
        huydk = dialog.findViewById(R.id.huydk);

        nam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                dky2.setEnabled(true);
            }
        });
        nu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                dky2.setEnabled(true);
            }
        });

        dky2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dk1 = ho.getText().toString().trim();
                String dk2 = ten.getText().toString().trim();
                String dk3 = dob.getText().toString().trim();
                String dk4 = copass.getText().toString().trim();
                Boolean b = !checkBox.isChecked();

                taikhoan = id.getText().toString().trim();
                matkhau = pass.getText().toString().trim();

                if (dk1.isEmpty() || dk2.isEmpty() || dk3.isEmpty() || dk4.isEmpty() || taikhoan.isEmpty() ||matkhau.isEmpty() || b ) {
                    showToast(0);
                }else if (!matkhau.equals(dk4)){
                    showToast(6);
                }else {
                    tk.setText(taikhoan);
                    mk.setText(matkhau);
                    dialog.cancel();
                    showToast(1);
                }
            }
        });
        huydk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    /**
     * Login
     */
    private void clickLogin() {
        str1 = tk.getText().toString().trim();
        str2 = mk.getText().toString().trim();
        if (!str1.isEmpty() || !str2.isEmpty()) {
            if(str1.equals(taikhoan) && str2.equals(matkhau)){
                showToast(2);
                Intent intent1 = new Intent(LoginRegisterActivity.this, ChooseTableActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
            }else if(str1.equals("1") && str2.equals("1")){
                showToast(3);
                Intent intent2 = new Intent(LoginRegisterActivity.this, ChooseTableActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
            }else {
                showToast(4);
            }
        }else {
            showToast(5);
        }

    }


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