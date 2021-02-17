package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "SubActivity";
    private FloatingActionButton fabPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Log.d(TAG, "onCreate: "+username );

//      gson 으로 json변환 putExtra로 넘기고 gson으로 자바오브젝트
        String userData = intent.getStringExtra("user3");
        Gson gson = new Gson();
        User user3 = gson.fromJson(userData, User.class);
        Log.d(TAG, "onCreate: =================================");
        Log.d(TAG, "onCreate User3: " + user3.getId());
        Log.d(TAG, "onCreate User3: " + user3.getUsername());
        Log.d(TAG, "onCreate User3: " + user3.getPassword());

//      serializable 객체 가져오기
        User user2 = (User) intent.getSerializableExtra("user2");
        Log.d(TAG, "onCreate: =================================");
        Log.d(TAG, "onCreate User2: " + user2.getId());
        Log.d(TAG, "onCreate User2: " + user2.getUsername());
        Log.d(TAG, "onCreate User2: " + user2.getPassword());

//      번들을 이용한 객체 가져오기

        Bundle bundle = intent.getBundleExtra("user1");
        User user1 = (User)bundle.getSerializable("user1");
//        User user1 = (User)intent.getExtras().get("user1");
        Log.d(TAG, "onCreate: =================================");
        Log.d(TAG, "onCreate User1: " + user1.getId());
        Log.d(TAG, "onCreate User1: " + user1.getUsername());
        Log.d(TAG, "onCreate User1: " + user1.getPassword());




        fabPop = findViewById(R.id.fab_pop);
        fabPop.setOnClickListener(v -> {
            // 인증이 성공함
            Intent newIntent = new Intent();
            newIntent.putExtra("auth", "ok");
            setResult(1, newIntent);
            finish(); // pop
        });

    }
}