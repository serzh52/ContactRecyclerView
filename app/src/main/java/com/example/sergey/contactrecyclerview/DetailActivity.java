package com.example.sergey.contactrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity {
    private TextView name;
    private TextView phone;
    SupportMapFragment mapFragment;
    GoogleMap map;
    Marker marker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*createMapView();
        addMarker();*/

        Contact contacts = (Contact) getIntent().getSerializableExtra("contact");

        name = (TextView) findViewById(R.id.details_name);
        phone = (TextView) findViewById(R.id.details_phone);

        name.setText(contacts.getName());
        phone.setText(contacts.getNumber());

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();
        if (map == null) {
            finish();
            return;
        }
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title(String.valueOf(name)));


    }
   /* private void createMapView(){
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    private void addMarker(){

        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                    .position(lng,lat)
                    .title("ррр")
                    .draggable(true)
            );
        }
    }*/

}
