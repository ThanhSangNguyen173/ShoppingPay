package com.example.shoppingpay.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.customview.CustomToastNotification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerActivity extends AppCompatActivity {

    DatabaseReference mData;
    EditText edt_key, edt_tablenumber;
    Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_owner);
        mData = FirebaseDatabase.getInstance().getReference();
        anhxa();

    }

    private void anhxa() {
        edt_key = findViewById(R.id.edt_key);
        edt_tablenumber = findViewById(R.id.edt_tablenumber);
        btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reset: resetTable();
                break;
        }
    }

    private void resetTable() {
        String key = edt_key.getText().toString();
        String tablenumber = edt_tablenumber.getText().toString();

        if (key.equals("173999")){
            switch (tablenumber){
                case "1":
                    mData.child("TB1").setValue("t");
                    break;
                case "2":
                    mData.child("TB2").setValue("t");
                    break;
                case "3":
                    mData.child("TB3").setValue("t");
                    break;
                case "4":
                    mData.child("TB4").setValue("t");
                    break;
                case "5":
                    mData.child("TB5").setValue("t");
                    break;
                case "6":
                    mData.child("TB6").setValue("t");
                    break;
                case "21":
                    mData.child("TB21").setValue("t");
                    break;
                case "22":
                    mData.child("TB22").setValue("t");
                    break;
                case "23":
                    mData.child("TB23").setValue("t");
                    break;
                case "24":
                    mData.child("TB24").setValue("t");
                    break;
                case "" :
                    mData.child("TB1").setValue("t");
                    mData.child("TB2").setValue("t");
                    mData.child("TB3").setValue("t");
                    mData.child("TB4").setValue("t");
                    mData.child("TB5").setValue("t");
                    mData.child("TB6").setValue("t");
                    mData.child("TB21").setValue("t");
                    mData.child("TB22").setValue("t");
                    mData.child("TB23").setValue("t");
                    mData.child("TB24").setValue("t");
                    break;
            }
            showToast(0);
            edt_tablenumber.setText("");
        }else {
            showToast(1);
        }
    }
    public void showToast(int i) {
        CustomToastNotification customToastNotification = new CustomToastNotification(this);
        customToastNotification.setBackground(R.color.DIVIDER_COLOR);
        switch (i) {
            case 0:
                customToastNotification.setMessage("Reset thành công!");
                break;
            case 1:
                customToastNotification.setMessage("Vui lòng nhập đúng keyword!");
                break;
        }
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setView(customToastNotification);
        toast.show();
    }
}