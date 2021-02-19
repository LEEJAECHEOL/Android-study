package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    private Toolbar toolbarMain;
    private TabLayout tabs;
    private ViewPager vpContainer;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbarMain);

        vpContainer = findViewById(R.id.vp_container);
        tabs = findViewById(R.id.tab);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), 1);

        myFragmentPagerAdapter.addFragment(new Frag1());
        myFragmentPagerAdapter.addFragment(new Frag2());

        vpContainer.setAdapter(myFragmentPagerAdapter);

        tabs.setupWithViewPager(vpContainer);
        tabs.getTabAt(0).setIcon(R.drawable.ic_app);
        tabs.getTabAt(1).setIcon(R.drawable.ic_assignment);


    }
}