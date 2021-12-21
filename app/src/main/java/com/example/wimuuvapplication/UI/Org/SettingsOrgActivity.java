package com.example.wimuuvapplication.UI.Org;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wimuuvapplication.R;

public class SettingsOrgActivity extends AppCompatActivity {
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsorg);

        logout = findViewById(R.id.logoutBtnOrg);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), OrgLoginActivity.class);
            }
        });
    }
}