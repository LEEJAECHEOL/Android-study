package com.example.mvvmex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.mvvmex1.model.Post;
import com.example.mvvmex1.viewmodel.PostViewModel;

public class MainActivity extends AppCompatActivity {

    private PostViewModel postViewModel;
    private static final String TAG = "MainActivity2";
    private Button btnChange;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.데이터초기화();
        postViewModel.구독().observe(this, posts -> {
            Log.d(TAG, "onCreate: 데이터 변경됨");
            // 
        });

        btnChange = findViewById(R.id.btn_change);
        btnChange.setOnClickListener(v -> {
            postViewModel.포스트한건추가(new Post(1, "title"));
        });
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> {
            postViewModel.포스트변경();
        });
    }
}