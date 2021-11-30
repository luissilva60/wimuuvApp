package com.example.wimuuvapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   private Button loginButton;
    private Button fgtpwButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginBTN);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClickLogin(View v) {
                Intent intent = new Intent(getApplicationContext(), Feed.class);


                startActivity(intent);
            }
        });

        fgtpwButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClickfgtpw(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);


                startActivity(intent);
            }
        });
    }
}