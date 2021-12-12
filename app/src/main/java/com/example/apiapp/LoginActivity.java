package com.example.apiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    User user;
    private final ArrayList<User> Users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.li);
        password = findViewById(R.id.pw);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadUsers();
    }

    private void loadUsers() {
        PGDuser pgd = new PGDuser();
        pgd.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()) {
                    User temp = data.getValue(User.class);
                    if (temp != null) {
                        temp.setKey(data.getKey());
                        Users.add(temp);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("INFO","error");

            }
        });
    }

    public void login(View view) {
        if(!username.getText().toString().equals("")){
            if(!password.getText().toString().equals("")){
                user= new User();
                user.setName(username.getText().toString());
                user.setPassword(password.getText().toString());
                if(checkExistingUser()){
                    connecting();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "Empty Password field, please enter a password",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Empty Username field, please enter a username",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkExistingUser() {
        for (User temp : Users) {
            if(temp.getName().equals(user.getName())){
                if(temp.getPassword().equals(user.getPassword())){
                    user = temp;
                    return true;
                }
            }
        }
        Toast.makeText(getApplicationContext(),
                "Any account with this Name and password",Toast.LENGTH_SHORT).show();
        return false;
    }

    private void connecting() {
        Toast.makeText(getApplicationContext(),
                "Connected",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RvActivity.class);
        intent.putExtra("MyUser", user);
        startActivity(intent);
    }

    public void Signup(View view) {
        Intent intent = new Intent(this, Signup.class);
        intent.putExtra("nbUser", ""+Users.size());
        intent.putExtra("Users", Users);
        startActivity(intent);
    }
}