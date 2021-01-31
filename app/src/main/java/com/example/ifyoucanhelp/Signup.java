package com.example.ifyoucanhelp;

import androidx.appcompat.app.AppCompatActivity;
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

import androidx.annotation.NonNull;

public class Signup extends AppCompatActivity {
EditText email,pwd,cpwd;
Button signup;
    FirebaseDatabase database;
    DatabaseReference ref;
    Register reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=(EditText)findViewById(R.id.emial);
        pwd=(EditText)findViewById(R.id.pwd);
        cpwd=(EditText)findViewById(R.id.cpwd);
        database=FirebaseDatabase.getInstance();
        signup=(Button)findViewById(R.id.signup);
        ref=database.getReference("Users");
        reg=new Register();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        getValue();
                        ref.child(email.getText().toString()).setValue(reg);
                        Toast.makeText(getApplicationContext(),"SIGNUP SUCCESSFULL",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



    }
    private void getValue(){
        reg.setEmail(email.getText().toString());
        reg.setPassword(cpwd.getText().toString());


    }

}
