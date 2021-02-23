package com.example.naverclone.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.naverclone.model.Product;
import com.example.naverclone.service.BlogApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {

    private static final String TAG = "ProductViewModel";

    private MutableLiveData<List<Product>> posts = new MutableLiveData<>();
    private BlogApi blogApi = BlogApi.retrofit.create(BlogApi.class);

    public MutableLiveData<List<Product>> 구독(){
        return posts;
    }

    public void init(){
        Call<List<Product>> call = blogApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> postsEntity = response.body();
//                Log.d(TAG, "onResponse: " + posts);
                posts.setValue(postsEntity);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: init() : Fail");
            }
        });
    }
    public void findById(int id){
        Call<List<Product>> call = blogApi.getProductsKeyword(id);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> postsEntity = response.body();
                posts.setValue(postsEntity);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: findById() : Fail");
            }
        });
    }

}
