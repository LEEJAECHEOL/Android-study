package com.example.mvvmex1.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmex1.model.Post;

import java.util.ArrayList;
import java.util.List;

//MainActivity에 대한 뷰 모델이라고 하자
public class PostViewModel extends ViewModel {

    // LiveData, MutableLiveData  변하지 않는 데이터, 변하는 데이터

    private MutableLiveData<List<Post>> mPost = new MutableLiveData<>();

    public MutableLiveData<List<Post>> 구독(){
        return mPost;
    }

    public void 포스트한건추가(Post post){
        List<Post> posts = mPost.getValue();
        posts.add(post);
        mPost.setValue(posts);
    }

    public void 포스트변경(){
        List<Post> posts = mPost.getValue();
        posts.get(0).setTitle("test");
        mPost.setValue(posts);
    }

    public void 데이터초기화(){
        List<Post> posts = new ArrayList<>();
        mPost.setValue(posts);
    }
}
