package com.example.naverclone.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naverclone.R;
import com.example.naverclone.model.SearchKeyword;
import com.example.naverclone.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchKeywordAdapter extends RecyclerView.Adapter<SearchKeywordAdapter.MyHolder> {
    private static final String TAG = "SearchKeywordAdapter";
    private List<SearchKeyword> keywords = new ArrayList<>();
    private final ProductViewModel productViewModel;

    public SearchKeywordAdapter(ProductViewModel productViewModel) {
        this.productViewModel = productViewModel;
    }

    public void setKeywords(List<SearchKeyword> keywords){
        this.keywords = keywords;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.keyword_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.setItem(keywords.get(position));
    }

    @Override
    public int getItemCount() {
        return keywords.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private Button btnKeyword;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            btnKeyword = itemView.findViewById(R.id.btn_keyword);
        }
        public void setItem(SearchKeyword searchKeyword){
            btnKeyword.setText(searchKeyword.getKeyword());

            btnKeyword.setOnClickListener(v -> {
                productViewModel.findById(keywords.get(getAdapterPosition()).getId());
            });
        }
    }
}
