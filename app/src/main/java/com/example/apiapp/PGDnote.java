package com.example.apiapp;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class PGDnote {
    private final DatabaseReference databaseReference;
    public PGDnote() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://apitest-e713f-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference(Tasks.class.getSimpleName());
    }
    public Task<Void> add(Tasks note){
        return databaseReference.push().setValue(note);
    }
    public Task<Void> update(String key, HashMap<String, Object> map) {
        return databaseReference.child(key).updateChildren(map);
    }
    public Task<Void> delete(String key){
        return databaseReference.child(key).removeValue();
    }
    public Query get() {
        Log.i("INFO","Query");
        return databaseReference.orderByKey();
    }
}
