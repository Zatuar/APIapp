package com.example.apiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {
    EditText title, body;
    private int idNote,idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        idNote = Integer.parseInt(getIntent().getStringExtra("Size"));
        idUser = Integer.parseInt(getIntent().getStringExtra("idUser"));
        title=findViewById(R.id.titleN);
        body=findViewById(R.id.bodyN);
    }

    public void addNewNote(View view) {
        if(!title.getText().toString().equals("")){
            PGDnote pgd = new PGDnote();
            Tasks note = new Tasks(title.getText().toString(),body.getText().toString(),idUser, idNote +1,false);
            pgd.add(note).addOnSuccessListener(suc ->
                    Toast.makeText(this,
                            "Note Created",Toast.LENGTH_SHORT).show()
            ).addOnFailureListener( er ->
                    Toast.makeText(this,
                            ""+er.getMessage(),Toast.LENGTH_SHORT).show()
            );
            finish();
        }
    }
}