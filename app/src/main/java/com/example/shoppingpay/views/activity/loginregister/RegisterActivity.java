package com.example.shoppingpay.views.activity.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shoppingpay.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //        Dialog dialog = new Dialog(LoginRegisterActivity.this);
//        dialog.setTitle("ĐĂNG KÝ TÀI KHOẢN");
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.dky_page);
//        dky2 = dialog.findViewById(R.id.dky2);
//        ho = dialog.findViewById(R.id.editTextHo);
//        ten = dialog.findViewById(R.id.editTextTen);
//        dob = dialog.findViewById(R.id.editTextDOB);
//        pass = dialog.findViewById(R.id.editTextPass);
//        copass = dialog.findViewById(R.id.editTextCoPass);
//        checkBox = dialog.findViewById(R.id.checkBox);
//        nam = dialog.findViewById(R.id.nam);
//        nu = dialog.findViewById(R.id.nu);
//        id = dialog.findViewById(R.id.editTextID);
//        huydk = dialog.findViewById(R.id.huydk);
//
//        dky2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String dk1 = ho.getText().toString().trim();
//                String dk2 = ten.getText().toString().trim();
//                String dk3 = dob.getText().toString().trim();
//                String dk4 = copass.getText().toString().trim();
//                Boolean b = !checkBox.isChecked();
//
//                taikhoan = id.getText().toString().trim();
//                matkhau = pass.getText().toString().trim();
//
//                if (dk1.isEmpty() || dk2.isEmpty() || dk3.isEmpty() || dk4.isEmpty() || taikhoan.isEmpty() ||matkhau.isEmpty() || b ) {
//                    showToast(0);
//                }else if (!matkhau.equals(dk4)){
//                    showToast(6);
//                }else {
//                    tk.setText(taikhoan);
//                    mk.setText(matkhau);
//                    dialog.cancel();
//                    showToast(1);
//                }
//            }
//        });
//        huydk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//        dialog.show();
    }
}