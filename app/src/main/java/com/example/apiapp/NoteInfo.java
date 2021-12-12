package com.example.apiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class NoteInfo extends AppCompatActivity {
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);
        data = getIntent().getStringExtra("MyNote");
        printNote();
    }

    private void printNote() {
        Gson gson = new Gson();
        Tasks note = gson.fromJson(this.data, Tasks.class);
        TextView titleNote = findViewById(R.id.titleN);
        titleNote.setText(note.getTitle());
        TextView bodyNote = findViewById(R.id.bodyN);
        bodyNote.setText(note.getBody());
    }
}