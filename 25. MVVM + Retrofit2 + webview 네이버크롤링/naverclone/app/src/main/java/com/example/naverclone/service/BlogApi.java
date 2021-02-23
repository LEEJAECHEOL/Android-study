package com.example.naverclone.service;

import com.example.naverclone.model.Product;
import com.example.naverclone.model.SearchKeyword;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BlogApi  {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.30.1.55:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // 키워드 목록 가져오기
    @GET("searchKeyword")
    Call<List<SearchKeyword>> getKeywords();

    // 글목록 가져오기
    @GET("product")
    Call<List<Product>> getProducts();

    // 키워드에 맞는 글목록 가져오기
    @GET("product/{id}")
    Call<List<Product>> getProductsKeyword(@Path("id") int id);




}
