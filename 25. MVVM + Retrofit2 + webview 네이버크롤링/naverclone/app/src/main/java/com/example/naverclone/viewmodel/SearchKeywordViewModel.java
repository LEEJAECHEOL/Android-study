package com.example.naverclone.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.naverclone.model.SearchKeyword;
import com.example.naverclone.service.BlogApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchKeywordViewModel extends ViewModel {

    private static final String TAG = "SearchKeywordViewModel";

    private MutableLiveData<List<SearchKeyword>> keywords = new MutableLiveData<>();
    private BlogApi blogApi = BlogApi.retrofit.create(BlogApi.class);

    public MutableLiveData<List<SearchKeyword>> 구독(){
        return keywords;
    }

    public void init(){
        Call<List<SearchKeyword>> call = blogApi.getKeywords();
        call.enqueue(new Callback<List<SearchKeyword>>() {
            @Override
            public void onResponse(Call<List<SearchKeyword>> call, Response<List<SearchKeyword>> response) {
                List<SearchKeyword> keywordsEntity = response.body();
//                Log.d(TAG, "onResponse: success" + keywordsEntity);
                keywords.setValue(keywordsEntity);
            }

            @Override
            public void onFailure(Call<List<SearchKeyword>> call, Throwable t) {
                Log.d(TAG, "onFailure: init() : Fail");
            }
        });
    }

}
