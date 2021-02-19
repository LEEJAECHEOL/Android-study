package com.example.myapplication10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TalksFragment extends Fragment {

    private RecyclerView rvList;
    private TalkAdapter talkAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_talks, container, false);
        rvList = view.findViewById(R.id.rv_talks_list);

        List<Talk> talks = new ArrayList<>();

        for(int i = 1; i < 30; i++){
            talks.add(new Talk(i, "user"+i, "message "+i, i + "시간전"));
        }
        LinearLayoutManager manager = new LinearLayoutManager(container.getContext(), RecyclerView.VERTICAL,false);
        rvList.setLayoutManager(manager);
        talkAdapter = new TalkAdapter(talks);
        rvList.setAdapter(talkAdapter);


        return view;

    }
}
