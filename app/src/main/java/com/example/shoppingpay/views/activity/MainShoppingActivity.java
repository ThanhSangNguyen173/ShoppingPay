package com.example.shoppingpay.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingpay.R;
import com.example.shoppingpay.api.BillApiService;
import com.example.shoppingpay.models.Bill;
import com.example.shoppingpay.models.CartItem;
import com.example.shoppingpay.tagcastscan.PaymentAcceptAnimation;
import com.example.shoppingpay.viewmodels.ShopViewModel;
import com.example.shoppingpay.views.activity.choosetable.ChooseTableActivity;
import com.example.shoppingpay.views.customview.CustomLoadingDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainShoppingActivity extends AppCompatActivity {

    NavController navController;
    ShopViewModel shopViewModel;
    private int cartQuantity = 0;
    TextView cartBadgeTextView;
    int bill_id, table_id, user_id;
    String serial, tablenumber, timein, value, token_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_main);

        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(MainShoppingActivity.this);

        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        },2500);

        Bundle bundle = getIntent().getExtras();
        serial = bundle.getString("seri");
        tablenumber = bundle.getString("table");
        timein = bundle.getString("timein");
        value = bundle.getString("value");
        token_user = bundle.getString("token_user");
        user_id = bundle.getInt("user_id");

        createNewBill();

        navController = Navigation.findNavController(MainShoppingActivity.this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(MainShoppingActivity.this,navController);
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                int quantity = 0;
                 for(CartItem cartItem : cartItems){
                     quantity += cartItem.getQuantity();
                 }
                 cartQuantity = quantity;
                 invalidateOptionsMenu();
            }
        });
    }

    private void createNewBill() {
        switch (tablenumber) {
            case "table1":
                table_id = 1;
                break;
            case "table2":
                table_id = 2;
                break;
            case "table3":
                table_id = 3;
                break;
            case "table4":
                table_id = 4;
                break;
            case "table5":
                table_id = 5;
                break;
            case "table6":
                table_id = 6;
                break;
            case "table21":
                table_id = 7;
                break;
            case "table22":
                table_id = 8;
                break;
            case "table23":
                table_id = 9;
                break;
            case "table24":
                table_id = 10;
                break;
        }
        BillApiService.billApiService.creatNewBill(token_user,table_id,user_id,timein,"-").enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {
                Bill bill = response.body();
                if(bill != null){
                bill_id = bill.getId();}
            }

            @Override
            public void onFailure(Call<Bill> call, Throwable t) {
                Log.d("API", t.toString());
            }
        });
    }


    public String getTimeIn(){return timein;}
    public String getTableNumer(){
        return tablenumber;
    }
    public String getSerial(){
        return serial;
    }
    public String getValueRating(){ return value;}
    public String getTokenUser(){return token_user;}
    public int getBillID(){return bill_id;}


    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        final MenuItem menuItem = menu.findItem(R.id.cartFragment);
        View actionView = menuItem.getActionView();

        cartBadgeTextView = actionView.findViewById(R.id.cart_badge_text_view);
        cartBadgeTextView.setText(String.valueOf(cartQuantity));
        cartBadgeTextView.setVisibility(cartQuantity==0 ? View.GONE : View.VISIBLE);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            return NavigationUI.onNavDestinationSelected(item,navController) ||
         super.onOptionsItemSelected(item);
    }
}