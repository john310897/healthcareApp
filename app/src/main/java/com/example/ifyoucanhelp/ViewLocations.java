package com.example.ifyoucanhelp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class ViewLocations extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase database;
    DatabaseReference ref;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("incidents");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String loc = ds.child("location").getValue().toString();
                        Geocoder gc = new Geocoder(getApplicationContext());
                        try {
                            List<Address> list = gc.getFromLocationName(loc, 20);
                            Address add = list.get(0);

                            double lat = add.getLatitude();
                            double lng = add.getLongitude();

                            LatLng latLng = new LatLng(lat, lng);

                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);
                            Geocoder geocoder = new Geocoder(getApplicationContext());
                            try {
                                List<Address> address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 2);
                                str = address.get(0).getLocality();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            markerOptions.title("" + str);

                            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            mMap.addMarker(markerOptions);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
