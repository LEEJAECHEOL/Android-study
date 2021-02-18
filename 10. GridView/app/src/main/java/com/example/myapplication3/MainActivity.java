package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity3";
    private Context mContext = MainActivity.this;
    private GridView gvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvMovie = findViewById(R.id.gv_movie);

        List<Movie> movies = new ArrayList<>();
        for (int i = 0;i < 12; i++){
            String num = i + 1 < 10 ? '0' + Integer.toString(i+1) : Integer.toString(i+1);
//            Log.d(TAG, "onCreate: "+ getApplicationContext().getResources().getIdentifier("mov"+ num, "drawable", mContext.getPackageName()));
            movies.add(new Movie(i, "제목"+i, getApplicationContext().getResources().getIdentifier("mov"+ num, "drawable", mContext.getPackageName())));
        }

        MovieItemAdapter adapter = new MovieItemAdapter(movies);
        gvMovie.setAdapter(adapter);




    }
}