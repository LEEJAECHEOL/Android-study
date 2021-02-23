package com.example.retroex1;

import com.example.retroex1.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface PostApi {

    @GET("/posts")
    Call<List<Post>> getPosts(); //여기서 해당주소에 요청해서 실패나 성공을 받아서 따로 처리해야하기 때문에 Call 이라는걸 쓴다.

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}