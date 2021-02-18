package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNoteList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvNoteList = findViewById(R.id.rv_note_list);
        rvNoteList.setLayoutManager(manager);

        List<Note> notes = new ArrayList<>();

        for(int i = 1; i < 50; i++){
            notes.add(new Note(i, "제목" + i, "서브 제목" + i, i));
        }
        noteAdapter = new NoteAdapter(notes);
        rvNoteList.setAdapter(noteAdapter);


    }
}