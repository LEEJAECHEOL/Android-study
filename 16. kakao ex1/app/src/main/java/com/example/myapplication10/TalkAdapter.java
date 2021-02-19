package com.example.myapplication10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.MyViewHolder> {

    private final List<Talk> talks;

    public TalkAdapter(List<Talk> talks) {
        this.talks = talks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chat_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(talks.get(position));
    }

    @Override
    public int getItemCount() {
        return talks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvMessage;
        private TextView tvTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvMessage = itemView.findViewById(R.id.tv_message);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
        public void setItem(Talk talk){
            tvUsername.setText(talk.getUsername());
            tvMessage.setText(talk.getMessage());
            tvTime.setText(talk.getTime());
        }
    }


}
