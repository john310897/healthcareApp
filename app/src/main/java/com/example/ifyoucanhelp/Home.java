package com.example.ifyoucanhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
Button mark,viewi;
String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mark=(Button)findViewById(R.id.mark);
        viewi=(Button)findViewById(R.id.viewin);
        user=getIntent().getStringExtra("user");
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,Gmap.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

        viewi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,ViewIncidents.class);
                startActivity(i);
            }
        });

    }
}
