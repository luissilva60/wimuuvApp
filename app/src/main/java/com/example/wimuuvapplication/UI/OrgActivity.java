package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;

public class OrgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org);
    }


    public void onClickLogoutOrg(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);


        startActivity(intent);
    }

}