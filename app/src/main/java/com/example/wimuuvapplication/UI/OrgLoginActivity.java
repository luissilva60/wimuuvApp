package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wimuuvapplication.R;

public class OrgLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_login);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), OrgActivity.class);


        startActivity(intent);
    }

    public void onClickfgtpw(View v) {
        Intent intent = new Intent(getApplicationContext(), ForgotPasswordOrg.class);


        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}