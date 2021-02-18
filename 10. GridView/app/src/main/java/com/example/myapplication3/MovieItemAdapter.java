package com.example.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieItemAdapter extends BaseAdapter {

    private static final String TAG = "MovieItemAdapter";
    private final List<Movie> movies;

    public MovieItemAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void addItem(Movie movie){
        movies.add(movie);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        movies.remove(position);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MainActivity mainActivity = (MainActivity)parent.getContext();

        LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.movie_item, parent, false);

        ImageView imgPost = view.findViewById(R.id.img_post);
        TextView tvTitle = view.findViewById(R.id.tv_title);

        imgPost.setImageResource(movies.get(position).getPic());
        tvTitle.setText(movies.get(position).getTitle());

        return view;
    }
}
