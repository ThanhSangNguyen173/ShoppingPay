package com.example.shoppingpay.views.activity.choosetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.MainShoppingActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsingTableActivity extends AppCompatActivity {

    TextView txt_tablenumber;
    Button btn_changetable,btn_gotomenu;
    String serial,tablenumber;
    DatabaseReference mData;
    ImageView img_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_using_table);

        mData = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        serial = bundle.getString("seri");
        tablenumber = bundle.getString("table");
        anhxa();

        tableNumber();
        setAnimation();
        clickLitener();
    }

    private void setAnimation() {
        Animation alpha2 = AnimationUtils.loadAnimation(this,R.anim.alpha2);
        img_table.startAnimation(alpha2);
    }

    private void tableNumber() {
        if(tablenumber.equals("table1")){
            txt_tablenumber.setText("Table 1"); }
        if(tablenumber.equals("table2")){
            txt_tablenumber.setText("Table 2"); }
        if(tablenumber.equals("table3")){
            txt_tablenumber.setText("Table 3"); }
        if(tablenumber.equals("table4")){
            txt_tablenumber.setText("Table 4"); }
        if(tablenumber.equals("table5")){
            txt_tablenumber.setText("Table 5"); }
        if(tablenumber.equals("table6")){
            txt_tablenumber.setText("Table 6"); }
        if(tablenumber.equals("table21")){
            txt_tablenumber.setText("Table 1 Floor 2"); }
        if(tablenumber.equals("table22")){
            txt_tablenumber.setText("Table 2 Floor 2"); }
        if(tablenumber.equals("table23")){
            txt_tablenumber.setText("Table 3 Floor 2"); }
        if(tablenumber.equals("table24")){
            txt_tablenumber.setText("Table 4 Floor 2"); }
    }

    private void clickLitener() {
        btn_changetable.setOnClickListener(this::OnClick);
        btn_gotomenu.setOnClickListener(this::OnClick);
    }

    private void OnClick(View view) {
        switch (view.getId()){
            case R.id.btn_changetable:
                changeTable();
                Intent intent2 = new Intent(UsingTableActivity.this, ChooseTableActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_gotomenu:
                Intent intent = new Intent(UsingTableActivity.this, MainShoppingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("seri",serial);
                bundle.putString("table",tablenumber);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    private void changeTable() {
        switch (tablenumber){
            case "table1":
                mData.child("TB1").setValue("t");
                break;
            case "table2":
                mData.child("TB2").setValue("t");
                break;
            case "table3":
                mData.child("TB3").setValue("t");
                break;
            case "table4":
                mData.child("TB4").setValue("t");
                break;
            case "table5":
                mData.child("TB5").setValue("t");
                break;
            case "table6":
                mData.child("TB6").setValue("t");
                break;
            case "table21":
                mData.child("TB21").setValue("t");
                break;
            case "table22":
                mData.child("TB22").setValue("t");
                break;
            case "table23":
                mData.child("TB23").setValue("t");
                break;
            case "table24":
                mData.child("TB24").setValue("t");
                break;
        }
    }

    private void anhxa() {
        txt_tablenumber = findViewById(R.id.txt_tablenumber);
        btn_changetable = findViewById(R.id.btn_changetable);
        btn_gotomenu = findViewById(R.id.btn_gotomenu);
        img_table = findViewById(R.id.img_table);

    }
}