package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;

public class PersonActivity extends AppCompatActivity {

    private Toolbar toolbarPerson;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        toolbarPerson = findViewById(R.id.toolbar_person);
        ivBack = findViewById(R.id.iv_back);
        setSupportActionBar(toolbarPerson);

        ivBack.setOnClickListener(v -> {
            finish();
        });

    }
}