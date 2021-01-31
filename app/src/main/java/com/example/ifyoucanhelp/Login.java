package com.example.ifyoucanhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText email,pwd;
    Button login;
    FirebaseDatabase database;
    DatabaseReference ref;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.editText);
        pwd=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.button3);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Users");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(email.getText().toString().equals("") && pwd.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "please enter valid details", Toast.LENGTH_LONG).show();
                        }else {
                            username = (String) dataSnapshot.child(email.getText().toString()).child("email").getValue();
                            password = (String) dataSnapshot.child(email.getText().toString()).child("password").getValue();
                            if (email.getText().toString().equals(username) && pwd.getText().toString().equals(password)) {
                                Toast.makeText(getApplicationContext(), "login successfull", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Login.this, Home.class);
                                i.putExtra("user", username);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "login unsuccessfull", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
