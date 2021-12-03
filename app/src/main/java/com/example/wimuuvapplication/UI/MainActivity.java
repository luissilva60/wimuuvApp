package com.example.wimuuvapplication.UI;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.wimuuvapplication.R;


public class MainActivity extends AppCompatActivity {
   private Button loginButton;
    private Button fgtpwButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginBTN);
        fgtpwButton = findViewById(R.id.forgotpw);

    }
    public void onClickLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), Feed.class);


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