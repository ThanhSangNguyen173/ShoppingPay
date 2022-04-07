package com.example.shoppingpay.LoginRegister;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.shoppingpay.tagcastscan.MainTagCastActivity;

public class LoginRegisterActivity extends AppCompatActivity {

    Button bt1, bt2,btnthoat;
    EditText tk, mk;
    //khai bao moi
    Button dky2, huydk;
    EditText ho,ten,dob,pass,copass,id;
    CheckBox checkBox;
    RadioButton nam,nu;
    String taikhoan,matkhau;
    //hết

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        bt1 = findViewById(R.id.login);
        bt2 = findViewById(R.id.dky);
        tk = findViewById(R.id.tk);
        mk = findViewById(R.id.mk);
        btnthoat = findViewById(R.id.btnthoat);
        controlbutton();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = tk.getText().toString().trim();
                String str2 = mk.getText().toString().trim();
                if (!str1.isEmpty() || !str2.isEmpty()) {
                    if(str1.equals(taikhoan) && str2.equals(matkhau)){
                        Intent intent1 = new Intent(LoginRegisterActivity.this, MainTagCastActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                        Toast.makeText(LoginRegisterActivity.this, "Welcome back, " + str1, Toast.LENGTH_SHORT).show();
                    }else if(str1.equals("1") && str2.equals("1")){
                        Intent intent2 = new Intent(LoginRegisterActivity.this, MainTagCastActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                        Toast.makeText(LoginRegisterActivity.this, "Welcome back, Sang đẹp trai ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginRegisterActivity.this, "Vui lòng kiểm tra lại ID/Password!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginRegisterActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            Toast.makeText(LoginRegisterActivity.this, "Vui lòng nhập đầy đủ thông tin và đồng ý điều khoản sử dụng!", Toast.LENGTH_SHORT).show();
                        }else {
                            tk.setText(taikhoan);
                            mk.setText(matkhau);
                            dialog.cancel();
                            Toast.makeText(LoginRegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
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

                //Hết
            }
        });
    }

    private void controlbutton() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginRegisterActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("BẠN ĐANG THOÁT KHỎI ỨNG DỤNG");
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