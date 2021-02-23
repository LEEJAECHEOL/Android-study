package com.example.movie1.service;

import com.example.movie1.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieApi {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.30.1.55:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("api/movie")
    Call<List<Movie>> getAllMovies();

    @DELETE("api/movie/{id}")
    Call<Void> deleteMovie(@Path("id") long id);
}
