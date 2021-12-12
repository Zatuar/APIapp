package com.example.apiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RvActivity extends AppCompatActivity {
    private final List<Tasks> Notes = new ArrayList<>();
    private RecyclerView NotesRV;
    private MyAdapter adapter;
    private PGDnote pgd;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        user= (User) getIntent().getSerializableExtra("MyUser");
        Log.i("User",user.getName());
        NotesRV = findViewById(R.id.Notes_List);
        firebaseDB();
    }


    private void firebaseDB() {
        pgd = new PGDnote();
        NotesRV.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        NotesRV.setLayoutManager(manager);
        adapter = new MyAdapter(this, Notes, pgd);
        NotesRV.setAdapter(adapter);
        Log.i("INFO","start");
        loadData();
        Log.i("INFO","end");
    }

    private void loadData() {

        pgd.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Notes.clear();
                ArrayList<Tasks> notes= new ArrayList<>();
                for (DataSnapshot data: snapshot.getChildren()) {
                    Tasks note = data.getValue(Tasks.class);
                    if (note != null) {
                        note.setKey(data.getKey());
                        if(note.getId_user() == user.getId()){
                            notes.add(note);
                        }
                    }
                }
                adapter.setItem(notes, item -> selectMe(item));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("INFO","error");

            }
        });
    }

    private void selectMe(Tasks item){
        Intent randomIntent = new Intent(this, NoteInfo.class);
        Gson gson = new Gson();
        String datatoString = gson.toJson(item);
        randomIntent.putExtra("MyNote", datatoString);
        startActivity(randomIntent);
    }

    public void addNote(View view) {
        Intent intent = new Intent(this,AddNote.class);
        Gson gson = new Gson();
        String datatoString = gson.toJson(Notes.size());
        intent.putExtra("Size", datatoString);
        intent.putExtra("idUser",""+user.getId());
        startActivity(intent);
    }
}