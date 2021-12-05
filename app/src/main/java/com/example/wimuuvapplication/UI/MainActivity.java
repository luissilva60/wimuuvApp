package com.example.wimuuvapplication.UI;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.wimuuvapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
   private Button loginButton;
    private Button fgtpwButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fgtpwButton = findViewById(R.id.forgotpw);
        loginButton = findViewById(R.id.loginBTN);

    }
    public void onClickLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);


        startActivity(intent);
    }

    public void onClickfgtpw(View v) {
        Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);


        startActivity(intent);
    }
    public void onClickSignup(View v) {
        Intent intent = new Intent(getApplicationContext(), Register.class);


        startActivity(intent);
    }


}