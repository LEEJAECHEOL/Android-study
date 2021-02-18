package com.example.myapplication8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication8.helper.NavigationViewHelper;
import com.google.android.material.navigation.NavigationView;

public class SubActivity extends AppCompatActivity {

    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        nv = findViewById(R.id.nv);
        NavigationViewHelper.enable(SubActivity.this, nv);
    }
}