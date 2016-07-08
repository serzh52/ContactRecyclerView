package com.example.sergey.contactrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity {
    private TextView name;
    private TextView phone;
    GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        createMapView();
        addMarker();

        Contact contacts = (Contact) getIntent().getSerializableExtra("contact");

        name = (TextView) findViewById(R.id.details_name);
        phone = (TextView) findViewById(R.id.details_phone);

        name.setText(contacts.getName());
        phone.setText(contacts.getNumber());

    }

    private void createMapView() {
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();

                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    private void addMarker() {
        if(googleMap!=null){
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(22.7253, 75.8655))
                    .title("Marker")
                    .draggable(true)
            );
        }
    }

   /*private void cameraPosition() {
CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(22.7253, 75.8655))
                        .zoom(5)
                        .bearing(45)
                        .tilt(20)
                        .build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                googleMap.animateCamera(cameraUpdate);
    }*/
}

