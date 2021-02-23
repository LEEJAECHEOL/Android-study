package com.example.naverclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.naverclone.adapter.ProductAdapter;
import com.example.naverclone.adapter.SearchKeywordAdapter;
import com.example.naverclone.viewmodel.ProductViewModel;
import com.example.naverclone.viewmodel.SearchKeywordViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    private SearchKeywordViewModel searchKeywordViewModel;
    private ProductViewModel productViewModel;

    private RecyclerView rvKeywordList;
    private RecyclerView rvBlogList;
    private ProductAdapter productAdapter;
    private SearchKeywordAdapter searchKeywordAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        searchKeywordViewModel.init();
        searchKeywordViewModel.구독().observe(this, searchKeywords -> {
            searchKeywordAdapter.setKeywords(searchKeywords);
        });

        productViewModel.init();
        productViewModel.구독().observe(this, products -> {
            productAdapter.setProducts(products);
        });

    }

    private void init(){
        searchKeywordViewModel = new ViewModelProvider(this).get(SearchKeywordViewModel.class);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // keyword
        rvKeywordList = findViewById(R.id.rv_keyword_list);
        searchKeywordAdapter = new SearchKeywordAdapter(productViewModel);
        LinearLayoutManager keywordManager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        rvKeywordList.setLayoutManager(keywordManager);
        rvKeywordList.setAdapter(searchKeywordAdapter);

        // blogList
        rvBlogList = findViewById(R.id.rv_blog_list);
        productAdapter = new ProductAdapter();
        LinearLayoutManager blogManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rvBlogList.setLayoutManager(blogManager);
        rvBlogList.setAdapter(productAdapter);

    }
}