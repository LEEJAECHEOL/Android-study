package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    private Toolbar toolbarMain; // androidx의 툴바
    private ImageView ivPerson, ivMenu;
    private Context mContext = MainActivity.this;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = findViewById(R.id.toolbar_main);
        drawer = findViewById(R.id.drawer);
        ivMenu = findViewById(R.id.iv_menu);

        setSupportActionBar(toolbarMain);

        ivPerson = findViewById(R.id.iv_person);

        ivPerson.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, PersonActivity.class);
            startActivity(intent);
        });
        ivMenu.setOnClickListener(v -> {
            drawer.openDrawer(GravityCompat.START);
        });


    }
}