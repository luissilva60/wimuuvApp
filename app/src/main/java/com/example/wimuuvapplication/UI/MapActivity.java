package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wimuuvapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public MapActivity(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this activity
        mView = inflater.inflate(R.layout.activity_map, container, false);
        return mView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.mapnav);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync((OnMapReadyCallback) this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        mGoogleMap = googleMap;
        googleMap. setMapType (GoogleMap. MAP_TYPE_NORMAL);
    }

    public void onClickMap(View v) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);


        startActivity(intent);
    }

    public void onClickFeed(View v) {
        Intent intent = new Intent(getApplicationContext(), Feed.class);


        startActivity(intent);
    }

    public void onClickProfile(View v) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);


        startActivity(intent);
    }

    public void onClickSettings(View v) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);


        startActivity(intent);
    }
}