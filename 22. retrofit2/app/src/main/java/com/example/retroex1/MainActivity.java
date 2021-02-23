package com.example.retroex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retroex1.Post;
import com.example.retroex1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainLog";

    private RecyclerView rvPost;
    private PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPost = findViewById(R.id.rv_post);

        PostApi postApi = PostApi.retrofit.create(PostApi.class);
        //https://jsonplaceholder.typicode.com/posts 에 데이터를 Call<>로 받아줌
        Call<List<Post>> call = postApi.getPosts(); //Call의 List의 call을 리턴해줌

        // 여기서부터 문법
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //응답 성공 했을 때
                List<Post> posts = response.body();

                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);

                rvPost.setLayoutManager(manager);

                postAdapter = new PostAdapter(posts);
//                postAdapter.notifyDataSetChanged();
                rvPost.setAdapter(postAdapter);


                Log.d(TAG, "onResponse: Posts"+posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패");
            }
        });
    }
}