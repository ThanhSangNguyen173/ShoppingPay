package com.example.shoppingpay.tagcastscan;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.example.shoppingpay.R;
import com.example.shoppingpay.api.BillApiService;
import com.example.shoppingpay.api.TableApiService;
import com.example.shoppingpay.models.Bill;
import com.example.shoppingpay.models.table.TableStatus;
import com.example.shoppingpay.views.activity.RateUsActivity;
import com.example.shoppingpay.views.customview.CustomToastNotification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentAcceptAnimation extends AppCompatActivity {
    TextView animation, txt_status;
    DatabaseReference mData;
    LottieAnimationView lottie;
    String tablenumber, timein, timeout, token_user;
    int bill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();
        setContentView(R.layout.payment_accept);

        mData = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        tablenumber = bundle.getString("table");
        timein = bundle.getString("timein");
        timeout = bundle.getString("timeout");
        bill_id = bundle.getInt("bill_id");
        token_user = bundle.getString("token_user");

        setDataTable();
        anhxa();
        animationSetup();
        setHandler();
        editDetailBill();
    }

    @Override
    public void onBackPressed() {
    }


    private void setHandler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PaymentAcceptAnimation.this, RateUsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("timein",timein);
                bundle.putString("timeout",timeout);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        },5000);
    }

    private void setDataTable() {
        switch (tablenumber){
            case "table1":
                updateStatusTable(1);
                mData.child("TB1").setValue("t");
                break;
            case "table2":
                updateStatusTable(2);
                mData.child("TB2").setValue("t");
                break;
            case "table3":
                updateStatusTable(3);
                mData.child("TB3").setValue("t");
                break;
            case "table4":
                updateStatusTable(4);
                mData.child("TB4").setValue("t");
                break;
            case "table5":
                updateStatusTable(5);
                mData.child("TB5").setValue("t");
                break;
            case "table6":
                updateStatusTable(6);
                mData.child("TB6").setValue("t");
                break;
            case "table21":
                updateStatusTable(7);
                mData.child("TB21").setValue("t");
                break;
            case "table22":
                updateStatusTable(8);
                mData.child("TB22").setValue("t");
                break;
            case "table23":
                updateStatusTable(9);
                mData.child("TB23").setValue("t");
                break;
            case "table24":
                updateStatusTable(10);
                mData.child("TB24").setValue("t");
                break;
        }
    }

    private void updateStatusTable(int id){

        TableApiService.tableApiService.updateTable(id,token_user,1).enqueue(new Callback<TableStatus>() {
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

    private void animationSetup() {
        animation.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(3000);
    }

    private void anhxa() {
        animation = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);
        txt_status = findViewById(R.id.txt_status);
    }

    private void editDetailBill(){

        BillApiService.billApiService.updateTimeOut(bill_id,token_user,timeout).enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {
                Log.d("API", response.body().toString());
            }

            @Override
            public void onFailure(Call<Bill> call, Throwable t) {
                Log.d("API", t.toString());
            }
        });
    }

}
