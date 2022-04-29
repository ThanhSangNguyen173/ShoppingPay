package com.example.shoppingpay.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingpay.R;
import com.example.shoppingpay.models.CartItem;
import com.example.shoppingpay.viewmodels.ShopViewModel;

import java.util.List;

public class MainShoppingActivity extends AppCompatActivity {

    NavController navController;
    ShopViewModel shopViewModel;
    private int cartQuantity = 0;
    TextView cartBadgeTextView;
    private String serial, tablenumber, timein, value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_main);

        Bundle bundle = getIntent().getExtras();
        serial = bundle.getString("seri");
        tablenumber = bundle.getString("table");
        timein = bundle.getString("timein");
        value = bundle.getString("value");

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

    public String getTimeIn(){return timein;}
    public String getTableNumer(){
        return tablenumber;
    }
    public String getSerial(){
        return serial;
    }
    public String getValueRating(){ return value;}

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