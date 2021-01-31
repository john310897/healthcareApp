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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import androidx.annotation.NonNull;

public class Mark_incedent extends AppCompatActivity {
    EditText loc,message;
    Button mark;
    FirebaseDatabase database;
    DatabaseReference ref;
    IncidentsRegister ireg;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_incedent);
        loc=(EditText)findViewById(R.id.mark_loc);
        message=(EditText)findViewById(R.id.msg);
        database=FirebaseDatabase.getInstance();
        mark=(Button)findViewById(R.id.add_marker);
        ref=database.getReference("incidents");
        ireg=new IncidentsRegister();
        String location=getIntent().getStringExtra("place");
        loc.setText(""+location);


        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(loc.getText().toString().equals("") || message.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"please mark the location/wait for the map to get load for getting location",Toast.LENGTH_LONG).show();
                }else {

                    Random r = new Random();
                    a = r.nextInt(80 - 65) + 65;

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            getValue();


                            ref.child("" + a).setValue(ireg);
                            Toast.makeText(getApplicationContext(), "INCIDENT MARKED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
            }
        });



    }
    private void getValue(){
        String currentTime = new SimpleDateFormat("HH:mm:ss a", Locale.getDefault()).format(new Date());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        ireg.setLocation(loc.getText().toString());
        ireg.setMessage(message.getText().toString());
        ireg.setTime(currentTime);
        ireg.setDate(currentDate);


    }

}
