package ghn.shopandgo;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by bukbukbukh on 11/13/16.
 */

public class SupermarketItemScan extends FragmentActivity implements KartManipulation {

    FragmentPagerAdapter adapterViewPager;
    int[] iconDrawables = {R.drawable.search_icon_2, R.drawable.keyboard_input, R.drawable.barcode_scanner_2};
    SuperMarketKart<SupermarketItem> mainKart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_market_login_activity);
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setTitle("ShopAndGo");
        toolbar.setTitleTextColor(Color.WHITE);


        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), this);
        vpPager.setAdapter(adapterViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(iconDrawables[i]);
        }

        ImageButton kart = (ImageButton) findViewById(R.id.kart_symbol);
        kart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });


    }

    @Override
    public void addToKart(SupermarketItem item) {
        mainKart.addToKart(item);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        private Context context;

        public MyPagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            this.context = context;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return ScanItemFragment.newInstance(0, "Search Item");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return ScanItemFragment.newInstance(1, "ID Input");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return BarcodeScannerFragment.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

    }
}
