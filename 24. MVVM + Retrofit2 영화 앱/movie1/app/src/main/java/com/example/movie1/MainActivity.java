package com.example.movie1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.movie1.viewmodel.MovieViewModel;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private MovieViewModel movieViewModel;
    private RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        movieViewModel.init();

        movieViewModel.구독().observe(this, posts -> {
            Log.d(TAG, "onCreate: " + posts);
            movieAdapter.setMovies(posts);
        });

    }
    private void init(){
        rvMovie = findViewById(R.id.rv_movie);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        movieAdapter = new MovieAdapter(movieViewModel);

        rvMovie.setLayoutManager(manager);
        rvMovie.setAdapter(movieAdapter);
    }

}