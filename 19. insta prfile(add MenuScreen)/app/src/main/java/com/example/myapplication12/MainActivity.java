package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

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
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        setSupportActionBar(toolbarMain);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Frag1()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()){

                case R.id.menu_home:
                    selectedFragment = new Frag1();
                    break;
                case R.id.menu_search:
                    selectedFragment = new Frag2();
                    break;
                case R.id.menu_plus:
                    selectedFragment = new Frag3();
                    break;
                case R.id.menu_heart:
                    selectedFragment = new Frag4();
                    break;
                case R.id.menu_user:
                    selectedFragment = new Frag5();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });




//
//        vpContainer = findViewById(R.id.vp_container);
//        tabs = findViewById(R.id.tab);
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), 1);
//
//        myFragmentPagerAdapter.addFragment(new ChildFrag1());
//        myFragmentPagerAdapter.addFragment(new ChildFrag2());
//
//        vpContainer.setAdapter(myFragmentPagerAdapter);
//
//        tabs.setupWithViewPager(vpContainer);
//        tabs.getTabAt(0).setIcon(R.drawable.ic_app);
//        tabs.getTabAt(1).setIcon(R.drawable.ic_assignment);


    }
}