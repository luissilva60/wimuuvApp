package com.example.wimuuvapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
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