package com.example.wimuuvapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        ImageView rightIcon = findViewById(R.id.right_icon);

    }
    public void onClickSettings(View v) {
        Intent intent = new Intent(getApplicationContext(),Settings.class);

        startActivity(intent);
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

}
