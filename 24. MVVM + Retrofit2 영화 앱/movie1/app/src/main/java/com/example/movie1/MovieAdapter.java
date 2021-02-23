package com.example.movie1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie1.model.Movie;
import com.example.movie1.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder> {

    private static final String TAG = "MovieAdapter";

    private List<Movie> movies = new ArrayList<>();
    private final MovieViewModel movieViewModel;

    public MovieAdapter(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
    }

    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.movie_item,parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private ImageView imPost;
        private TextView tvTitle;
        private RatingBar ratingBar;
        private ImageView ivDelete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imPost = itemView.findViewById(R.id.iv_post);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
        public void setItem(Movie movie){
            Glide
                .with(imPost.getContext())
                .load(movie.getMedium_cover_image())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imPost);

            tvTitle.setText(movie.getTitle());
            ratingBar.setRating((float)movie.getRating());

            ivDelete.setOnClickListener(v -> {
                movieViewModel.remove(getAdapterPosition());
            });
        }
    }
}
