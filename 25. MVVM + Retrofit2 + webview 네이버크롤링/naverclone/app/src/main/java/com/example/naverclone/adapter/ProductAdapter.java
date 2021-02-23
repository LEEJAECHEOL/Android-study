package com.example.naverclone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.naverclone.DetailActivity;
import com.example.naverclone.R;
import com.example.naverclone.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> {

    private List<Product> products = new ArrayList<>();

    public void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.blog_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.setItem(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        private ImageView ivThumbnail;
        private TextView tvTitle;
        private TextView tvDay;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDay = itemView.findViewById(R.id.tv_day);
        }
        public void setItem(Product product){
            Glide
                .with(ivThumbnail.getContext())
                .load(product.getThumnail())
                .centerCrop()
                .placeholder(R.drawable.ic_image)
                .into(ivThumbnail);
            tvTitle.setText(product.getTitle());
            tvDay.setText(product.getDay());
            tvTitle.setOnClickListener(v -> {
                Context context = tvTitle.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("url", product.getBlogUrl());
                context.startActivity(intent);
            });
        }
    }
}
