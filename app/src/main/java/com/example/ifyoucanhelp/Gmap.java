package com.example.ifyoucanhelp;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Gmap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
         user=getIntent().getStringExtra("user");
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
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.M)
            public String str="";
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                Geocoder geocoder=new Geocoder(getApplicationContext());
                try {
                    List<Address> address=geocoder.getFromLocation(latLng.latitude,latLng.longitude,2);
                     str=address.get(0).getLocality();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                markerOptions.title(""+str);

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                mMap.addMarker(markerOptions);

                Intent i=new Intent(Gmap.this,Mark_incedent.class);
                i.putExtra("place",str);
                i.putExtra("user",user);
                startActivity(i);

            }
        });
    }
}
