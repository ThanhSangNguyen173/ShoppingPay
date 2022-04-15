package com.example.shoppingpay;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroViewAdapter extends PagerAdapter {

    Context mContext;
    List<ScreenAdvertisement> mListScreen;

    public IntroViewAdapter(Context mContext, List<ScreenAdvertisement> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
