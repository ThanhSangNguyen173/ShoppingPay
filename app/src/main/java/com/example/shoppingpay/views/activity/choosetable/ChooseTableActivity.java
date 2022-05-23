package com.example.shoppingpay.views.activity.choosetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingpay.R;
import com.example.shoppingpay.api.TableApiService;
import com.example.shoppingpay.models.table.Table1;
import com.example.shoppingpay.models.table.Table2;
import com.example.shoppingpay.models.table.Table21;
import com.example.shoppingpay.models.table.Table22;
import com.example.shoppingpay.models.table.Table23;
import com.example.shoppingpay.models.table.Table24;
import com.example.shoppingpay.models.table.Table3;
import com.example.shoppingpay.models.table.Table4;
import com.example.shoppingpay.models.table.Table5;
import com.example.shoppingpay.models.table.Table6;
import com.example.shoppingpay.models.table.TableStatus;
import com.example.shoppingpay.tagcastscan.MainTagCastActivity;

import com.example.shoppingpay.views.customview.CustomLoadingDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseTableActivity extends AppCompatActivity {
    DatabaseReference mData;
    String pickserial, tablenumber, valuerat, value, serial1,serial2,serial3,serial4,serial5,serial6,serial21,serial22,serial23,serial24;
    String tb1,tb2,tb3,tb4,tb5,tb6,tb21,tb22,tb23,tb24;
    int user_id;
    TabHost tabHost;
    LinearLayout item_note_status;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4,imgbtn5,imgbtn6,imgbtn21,imgbtn22,imgbtn23,imgbtn24;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.activity_table);
        mData = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");

        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(ChooseTableActivity.this);

        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
                setRatingData();
                tabHost.setVisibility(View.VISIBLE);
                item_note_status.setVisibility(View.VISIBLE);
            }
    },5000);

        anhxa();
        setTabHost();
        clickListener();
        getTableApiService();
    }

    private void setTabHost() {
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
    }

    private void anhxa() {
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
        item_note_status = findViewById(R.id.item_note_status);

        tabHost.setVisibility(View.INVISIBLE);
    }

    private void setRatingData() {
        mData.child("Rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                valuerat = snapshot.getValue().toString();
                value = valuerat.substring(0,3);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_table1:
                pickserial = serial1;
                mData.child("TB1").setValue("f");
                tablenumber = "table1";
                GetDataTable();
                updateStatusTable(1);
                break;
            case R.id.btn_table2:
                pickserial =serial2;
                mData.child("TB2").setValue("f");
                tablenumber = "table2";
                GetDataTable();
                updateStatusTable(2);
                break;
            case R.id.btn_table3:
                pickserial =serial3;
                mData.child("TB3").setValue("f");
                tablenumber = "table3";
                GetDataTable();
                updateStatusTable(3);
                break;
            case R.id.btn_table4:
                pickserial =serial4;
                mData.child("TB4").setValue("f");
                tablenumber = "table4";
                GetDataTable();
                updateStatusTable(4);
                break;
            case R.id.btn_table5:
                pickserial =serial5;
                mData.child("TB5").setValue("f");
                tablenumber = "table5";
                GetDataTable();
                updateStatusTable(5);
                break;
            case R.id.btn_table6:
                pickserial =serial6;
                mData.child("TB6").setValue("f");
                tablenumber = "table6";
                GetDataTable();
                updateStatusTable(6);
                break;
            case R.id.btn_table21:
                pickserial =serial21;
                mData.child("TB21").setValue("f");
                tablenumber = "table21";
                GetDataTable();
                updateStatusTable(7);
                break;
            case R.id.btn_table22:
                pickserial =serial22;
                mData.child("TB22").setValue("f");
                tablenumber = "table22";
                GetDataTable();
                updateStatusTable(8);
                break;
            case R.id.btn_table23:
                pickserial =serial23;
                mData.child("TB23").setValue("f");
                tablenumber = "table23";
                GetDataTable();
                updateStatusTable(9);
                break;
            case R.id.btn_table24:
                pickserial =serial24;
                mData.child("TB24").setValue("f");
                tablenumber = "table24";
                GetDataTable();
                updateStatusTable(10);
                break;
        }
    }

/**CALL API GET STATUS TABLE
 */
    private void getTableApiService() {
            getAPITable1();
            getAPITable2();
            getAPITable3();
            getAPITable4();
            getAPITable5();
            getAPITable6();
            getAPITable21();
            getAPITable22();
            getAPITable23();
            getAPITable24();
    }

    private void getAPITable24() {
        TableApiService.tableApiService.getApiTable24().enqueue(new Callback<Table24>() {
            @Override
            public void onResponse(Call<Table24> call, Response<Table24> response) {
                Table24 table24 = response.body();
                if(table24 != null){
                    int STATUS = table24.getStatus();
                    if(STATUS==0){imgbtn24.setEnabled(false);}else {imgbtn24.setEnabled(true);}
                    serial24 = table24.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table24> call, Throwable t) {
                getAPITable24();
            }
        });
    }
    private void getAPITable23() {
        TableApiService.tableApiService.getApiTable23().enqueue(new Callback<Table23>() {
            @Override
            public void onResponse(Call<Table23> call, Response<Table23> response) {
                Table23 table23 = response.body();
                if(table23 != null){
                    int STATUS = table23.getStatus();
                    if(STATUS==0){imgbtn23.setEnabled(false);}else {imgbtn23.setEnabled(true);}
                    serial23 = table23.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table23> call, Throwable t) {
                getAPITable23();
            }
        });
    }
    private void getAPITable22() {
        TableApiService.tableApiService.getApiTable22().enqueue(new Callback<Table22>() {
            @Override
            public void onResponse(Call<Table22> call, Response<Table22> response) {
                Table22 table22 = response.body();
                if(table22 != null){
                    int STATUS = table22.getStatus();
                    if(STATUS==0){imgbtn22.setEnabled(false);}else {imgbtn22.setEnabled(true);}
                    serial22 = table22.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table22> call, Throwable t) {
                getAPITable22();
            }
        });
    }
    private void getAPITable21() {
        TableApiService.tableApiService.getApiTable21().enqueue(new Callback<Table21>() {
            @Override
            public void onResponse(Call<Table21> call, Response<Table21> response) {
                Table21 table21 = response.body();
                if(table21 != null){
                    int STATUS = table21.getStatus();
                    if(STATUS==0){imgbtn21.setEnabled(false);}else {imgbtn21.setEnabled(true);}
                    serial21 = table21.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table21> call, Throwable t) {
                getAPITable21();
            }
        });
    }
    private void getAPITable6() {
        TableApiService.tableApiService.getApiTable6().enqueue(new Callback<Table6>() {
            @Override
            public void onResponse(Call<Table6> call, Response<Table6> response) {
                Table6 table6 = response.body();
                if(table6 != null){
                    int STATUS = table6.getStatus();
                    if(STATUS==0){imgbtn6.setEnabled(false);}else {imgbtn6.setEnabled(true);}
                    serial6 = table6.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table6> call, Throwable t) {
                getAPITable6();
            }
        });
    }
    private void getAPITable5() {
        TableApiService.tableApiService.getApiTable5().enqueue(new Callback<Table5>() {
            @Override
            public void onResponse(Call<Table5> call, Response<Table5> response) {
                Table5 table5 = response.body();
                if(table5 != null){
                    int STATUS = table5.getStatus();
                    if(STATUS==0){imgbtn5.setEnabled(false);}else {imgbtn5.setEnabled(true);}
                    serial5 = table5.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table5> call, Throwable t) {
                getAPITable5();
            }
        });
    }
    private void getAPITable4() {
        TableApiService.tableApiService.getApiTable4().enqueue(new Callback<Table4>() {
            @Override
            public void onResponse(Call<Table4> call, Response<Table4> response) {
                Table4 table4 = response.body();
                if(table4 != null){
                    int STATUS = table4.getStatus();
                    if(STATUS==0){imgbtn4.setEnabled(false);}else {imgbtn4.setEnabled(true);}
                    serial4 = table4.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table4> call, Throwable t) {
                getAPITable4();
            }
        });
    }
    private void getAPITable3() {
        TableApiService.tableApiService.getApiTable3().enqueue(new Callback<Table3>() {
            @Override
            public void onResponse(Call<Table3> call, Response<Table3> response) {
                Table3 table3 = response.body();
                if(table3 != null){
                    int STATUS = table3.getStatus();
                    if(STATUS==0){imgbtn3.setEnabled(false);}else {imgbtn3.setEnabled(true);}
                    serial3 = table3.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table3> call, Throwable t) {
                getAPITable3();
            }
        });
    }
    private void getAPITable1(){
        TableApiService.tableApiService.getApiTable1().enqueue(new Callback<Table1>() {
            @Override
            public void onResponse(Call<Table1> call, Response<Table1> response) {
                Table1 table1 = response.body();
                if(table1 != null){
                    int STATUS = table1.getStatus();
                    if(STATUS==0){imgbtn1.setEnabled(false);}else {imgbtn1.setEnabled(true);}
                    serial1 = table1.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table1> call, Throwable t) {
                getAPITable1();
            }
        });
    }
    private void getAPITable2() {
        TableApiService.tableApiService.getApiTable2().enqueue(new Callback<Table2>() {
            @Override
            public void onResponse(Call<Table2> call, Response<Table2> response) {
                Table2 table2 = response.body();
                if(table2 != null){
                    int STATUS = table2.getStatus();
                    if(STATUS==0){imgbtn2.setEnabled(false);}else {imgbtn2.setEnabled(true);}
                    serial2 = table2.getSerial_tagcast_id();
                }
            }

            @Override
            public void onFailure(Call<Table2> call, Throwable t) {
                getAPITable2();
            }
        });
    }

    private void GetDataTable() {
                Intent intent = new Intent(ChooseTableActivity.this, MainTagCastActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_id",user_id);
                bundle.putString("seri",pickserial);
                bundle.putString("table",tablenumber);
                bundle.putString("value",value);
                intent.putExtras(bundle);
                startActivity(intent);
    }

    private void updateStatusTable(int id){

        TableApiService.tableApiService.updateTable(id,0).enqueue(new Callback<TableStatus>() {
            @Override
            public void onResponse(Call<TableStatus> call, Response<TableStatus> response) {
                Log.d("API", response.toString());
            }

            @Override
            public void onFailure(Call<TableStatus> call, Throwable t) {
                updateStatusTable(id);
            }
        });
    }
}
