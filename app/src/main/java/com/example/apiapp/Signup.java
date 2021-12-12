package com.example.apiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity {
    EditText name,email,pw;
    private int id_user;
    private ArrayList<User> Users = new ArrayList<>();
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        id_user = Integer.parseInt(getIntent().getStringExtra("nbUser"));
        Users= (ArrayList<User>) getIntent().getSerializableExtra("Users");
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pw=findViewById(R.id.password);
    }

    public void addNewUser(View view) {
        if (!(name.getText().toString().equals("") || email.getText().toString().equals("") || pw.getText().toString().equals(""))) {
            user = new User(id_user + 1, name.getText().toString(), email.getText().toString(), pw.getText().toString());
            if(!unexistingUser()){
                PGDuser pgd = new PGDuser();
                pgd.add(user).addOnSuccessListener(suc ->
                        Toast.makeText(this,
                                "Account Created", Toast.LENGTH_SHORT).show()
                ).addOnFailureListener(er ->
                        Toast.makeText(this,
                                "" + er.getMessage(), Toast.LENGTH_SHORT).show()
                );
                finish();
            }
            else{
                Toast.makeText(this,
                        "User name or email address already existing ", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,
                    "A field is empty, please complete it", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean unexistingUser() {
        for (User temp : Users) {
            if(temp.getName().equals(user.getName())){
                return true;
            }
        }
        return false;
    }
}