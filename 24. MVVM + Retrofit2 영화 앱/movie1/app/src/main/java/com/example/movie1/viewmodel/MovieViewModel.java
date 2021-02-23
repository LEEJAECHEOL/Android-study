package com.example.movie1.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie1.model.Movie;
import com.example.movie1.service.MovieApi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private static final String TAG = "MovieViewModel";

    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private MovieApi movieApi = MovieApi.retrofit.create(MovieApi.class);

    public MutableLiveData<List<Movie>> 구독(){
        return movies;
    }
    public void init(){
        Call<List<Movie>> call = movieApi.getAllMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movieEntity = response.body();
                movies.setValue(movieEntity);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d(TAG, "onFailure: GET Fail");
            }
        });
    }
    public void remove(int position){
        List<Movie> movieData = movies.getValue();
        long id = movieData.get(position).getId();
        Call<Void> call = movieApi.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if(response.code() == 200){
                    movieData.remove(position);
                    movies.setValue(movieData);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: DELETE Fail" );
            }
        });

    }


}
