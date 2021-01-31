package com.example.ifyoucanhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewIncidents extends AppCompatActivity {
ListView l;
FirebaseDatabase database;
DatabaseReference ref;
ArrayList<String> list;
ArrayAdapter<String> adapter;
Incidents incidents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incidents);
        l= findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("incidents");
        incidents=new Incidents();
        list=new ArrayList<>();
        adapter= new ArrayAdapter<String>(this, R.layout.incident_info, R.id.textView, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){

                   String loc=ds.child("location").getValue().toString();
                   String msg=ds.child("message").getValue().toString();
                   String time=ds.child("time").getValue().toString();
                   String date=ds.child("date").getValue().toString();

                    list.add("location :"+loc+" \n message: "+ msg+"\n time: "+time+"\n Date :"+date);


                }
                l.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
