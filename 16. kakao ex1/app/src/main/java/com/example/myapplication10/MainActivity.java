package com.example.myapplication10;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.Icon;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    boolean[] isChecked = {false, true, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        int[] icons = {
//                R.string.fa_user, R.string.fa_comment, R.string.fa_hashtag_solid
//        };
        bottomNavigationView = findViewById(R.id.bottom_menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.kakao_container, new TalksFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.bottom_talks);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.bottom_friends:
                    selectedFragment = new FriendsFragment();
                    break;
                case R.id.bottom_talks:

                    selectedFragment = new TalksFragment();
                    break;
                case R.id.bottom_tags:
                    selectedFragment = new TagsFragment();
                    break;
                case R.id.bottom_more:
                    selectedFragment = new MoreFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.kakao_container, selectedFragment).commit();
            return true;
        });

    }
}