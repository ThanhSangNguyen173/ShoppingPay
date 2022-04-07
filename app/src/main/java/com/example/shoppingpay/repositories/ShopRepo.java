package com.example.shoppingpay.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingpay.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts(){
        if (mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(),"BeefSteak","27",true, "https://res.klook.com/image/upload/c_fill,w_800,h_533/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/zyulungyz94xotkznw79.webp"));
        productList.add(new Product(UUID.randomUUID().toString(),"French Fries","9",true, "https://www.thespruceeats.com/thmb/IHKuXcx3uUI1IWkM_cnnQdFH-zQ=/3485x2323/filters:fill(auto,1)/how-to-make-homemade-french-fries-2215971-hero-01-02f62a016f3e4aa4b41d0c27539885c3.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),"Salmon Salad","15",true,"https://www.recipetineats.com/wp-content/uploads/2019/07/Salmon-Salad_1-SQ.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),"Pizza Pepperoni","32",true,"https://www.pizzaexpress.vn/wp-content/uploads/2020/08/pp1.jpeg"));
        productList.add(new Product(UUID.randomUUID().toString(),"BBCharcoal Burger","23",false,"https://cdn.tgdd.vn/2021/04/CookProduct/1200-1200x676-87.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),"Seafood Spaghetti","29",true,"https://cdn.daynauan.info.vn/wp-content/uploads/2018/03/mi-y-hai-san.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),"Tomato Soup","22",true,"https://pinchofyum.com/wp-content/uploads/Homemade-Tomato-Soup-Square.png"));
        productList.add(new Product(UUID.randomUUID().toString(),"Foie Gras","44",true,"https://my-test-11.slatic.net/p/adfac7e86831e9c9d10ac830a49c758d.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),"Red Wine","29",true,"https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F9%2F2022%2F02%2F24%2FRed-Wine-Longevity-FT-BLOG0222.jpg&q=60"));
        productList.add(new Product(UUID.randomUUID().toString(),"Coke","6",true,"https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/RJFYQ5HD4MI6XCGFJ7LDQLCHZM.jpg&w=1200"));
        productList.add(new Product(UUID.randomUUID().toString(),"Apple Cake","12",false,"https://thumbs.dreamstime.com/b/eplekake-traditional-norwegian-apple-cake-pie-sprinkled-sugar-cinnamon-old-rustic-wooden-table-autumn-harvest-apples-159343965.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),"Choco Muffin","8",true,"https://4.imimg.com/data4/II/BH/MY-10298091/chocolate-muffin-500x500.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(),"Black Thuan","1",true,"https://scontent.fdad1-2.fna.fbcdn.net/v/t39.30808-1/258572136_2064787253675931_8263201712600819377_n.jpg?stp=c0.145.320.320a_dst-jpg_p320x320&_nc_cat=102&ccb=1-5&_nc_sid=7206a8&_nc_ohc=Tss_W6bLFJYAX8JtWG6&_nc_ht=scontent.fdad1-2.fna&oh=00_AT_BZCgYYJOGctVLcAB6AoGUbdS5fxHvegt1He1Otf38Ig&oe=62542A70"));
        mutableProductList.setValue(productList);
    }
}
