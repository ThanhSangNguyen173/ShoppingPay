package com.example.shoppingpay.views.choosetable;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingpay.R;
import com.example.shoppingpay.tagcastscan.MainTagCastActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseTableActivity extends AppCompatActivity {
    TextView textView;
    DatabaseReference mData;
    String pickserial, tablenumber;
    String tb1,tb2,tb3,tb4,tb5,tb6,tb21,tb22,tb23,tb24;
    TabHost tabHost;
    Button btn_checktable;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4,imgbtn5,imgbtn6,imgbtn21,imgbtn22,imgbtn23,imgbtn24;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_table);
        mData = FirebaseDatabase.getInstance().getReference();

        anhxa();
        clickListener();

        Animation alpha2 = AnimationUtils.loadAnimation(this,R.anim.alpha2);
        btn_checktable.startAnimation(alpha2);

        tabHost.setup();
        TabHost.TabSpec spec1,spec2;

        spec1 = tabHost.newTabSpec("t1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Floor 1");
        tabHost.addTab(spec1);

        spec2 = tabHost.newTabSpec("t2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Floor 2");
        tabHost.addTab(spec2);
    }

    private void getTableStatus() {
        mData.child("TB1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb1 = snapshot.getValue().toString();
                if(tb1.equals("f")){imgbtn1.setEnabled(false);}else imgbtn1.setEnabled(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb2 = snapshot.getValue().toString();
                if(tb2.equals("f")){imgbtn2.setEnabled(false);}else imgbtn2.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb3 = snapshot.getValue().toString();
                if(tb3.equals("f")){imgbtn3.setEnabled(false);}else imgbtn3.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb4 = snapshot.getValue().toString();
                if(tb4.equals("f")){imgbtn4.setEnabled(false);}else imgbtn4.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb5 = snapshot.getValue().toString();
                if(tb5.equals("f")){imgbtn5.setEnabled(false);}else imgbtn5.setEnabled(true);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb6 = snapshot.getValue().toString();
                if(tb6.equals("f")){imgbtn6.setEnabled(false);}else imgbtn6.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB21").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb21 = snapshot.getValue().toString();
                if(tb21.equals("f")){imgbtn21.setEnabled(false);}else imgbtn21.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB22").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb22 = snapshot.getValue().toString();
                if(tb22.equals("f")){imgbtn22.setEnabled(false);}else imgbtn22.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB23").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb23 = snapshot.getValue().toString();
                if(tb23.equals("f")){imgbtn23.setEnabled(false);}else imgbtn23.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        mData.child("TB24").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tb24 = snapshot.getValue().toString();
                if(tb24.equals("f")){imgbtn24.setEnabled(false);}else imgbtn24.setEnabled(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTableActivity.this, "Lỗi dữ liệu, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickListener() {
        imgbtn1.setOnClickListener(this::onClick);
        imgbtn2.setOnClickListener(this::onClick);
        imgbtn3.setOnClickListener(this::onClick);
        imgbtn4.setOnClickListener(this::onClick);
        imgbtn5.setOnClickListener(this::onClick);
        imgbtn6.setOnClickListener(this::onClick);
        imgbtn21.setOnClickListener(this::onClick);
        imgbtn22.setOnClickListener(this::onClick);
        imgbtn23.setOnClickListener(this::onClick);
        imgbtn24.setOnClickListener(this::onClick);
        btn_checktable.setOnClickListener(this::onClick);
    }

    private void anhxa() {
        btn_checktable = findViewById(R.id.checktable);
        tabHost = findViewById(R.id.tabhost_table);
        imgbtn1 = findViewById(R.id.btn_table1);
        imgbtn2 = findViewById(R.id.btn_table2);
        imgbtn3 = findViewById(R.id.btn_table3);
        imgbtn4 = findViewById(R.id.btn_table4);
        imgbtn5 = findViewById(R.id.btn_table5);
        imgbtn6 = findViewById(R.id.btn_table6);
        imgbtn21 = findViewById(R.id.btn_table21);
        imgbtn22 = findViewById(R.id.btn_table22);
        imgbtn23 = findViewById(R.id.btn_table23);
        imgbtn24 = findViewById(R.id.btn_table24);

        tabHost.setVisibility(View.INVISIBLE);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checktable:
                getTableStatus();
                tabHost.setVisibility(View.VISIBLE);
                btn_checktable.clearAnimation();
                btn_checktable.setVisibility(View.GONE);
                break;
            case R.id.btn_table1:
                pickserial = "1";
                mData.child("TB1").setValue("f");
                tablenumber = "table1";
                GetDataTable();
                break;
            case R.id.btn_table2:
                pickserial ="JPN-PPER-0099-9636-8472";
                mData.child("TB2").setValue("f");
                tablenumber = "table2";
                GetDataTable();
                break;
            case R.id.btn_table3:
                pickserial ="JPN-PPER-0292-8289-7478";
                mData.child("TB3").setValue("f");
                tablenumber = "table3";
                GetDataTable();
                break;
            case R.id.btn_table4:
                pickserial ="4";
                mData.child("TB4").setValue("f");
                tablenumber = "table4";
                GetDataTable();
                break;
            case R.id.btn_table5:
                pickserial ="5";
                mData.child("TB5").setValue("f");
                tablenumber = "table5";
                GetDataTable();
                break;
            case R.id.btn_table6:
                pickserial ="6";
                mData.child("TB6").setValue("f");
                tablenumber = "table6";
                GetDataTable();
                break;
            case R.id.btn_table21:
                pickserial ="JPN-PPER-0099-9636-8472";
                mData.child("TB21").setValue("f");
                tablenumber = "table21";
                GetDataTable();
                break;
            case R.id.btn_table22:
                pickserial ="22";
                mData.child("TB22").setValue("f");
                tablenumber = "table22";
                GetDataTable();
                break;
            case R.id.btn_table23:
                pickserial ="JPN-PPER-0292-8289-7478";
                mData.child("TB23").setValue("f");
                tablenumber = "table23";
                GetDataTable();
                break;
            case R.id.btn_table24:
                pickserial ="24";
                mData.child("TB24").setValue("f");
                tablenumber = "table24";
                GetDataTable();
                break;
        }
    }

    private void GetDataTable() {
                Intent intent = new Intent(ChooseTableActivity.this, MainTagCastActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("seri",pickserial);
                bundle.putString("table",tablenumber);
                intent.putExtras(bundle);
                startActivity(intent);
    }
}
