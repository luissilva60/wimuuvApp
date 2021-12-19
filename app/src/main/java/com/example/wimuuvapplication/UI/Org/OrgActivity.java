package com.example.wimuuvapplication.UI.Org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.Student.MapsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org);

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(navListener);

        Fragment fragment = new MapsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout,fragment)
                .commit();
    }


    public void onClickLogoutOrg(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);


        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_map:
                            selectedFragment = new MapsOrgFragment();
                            break;
                        case R.id.nav_feed:
                            selectedFragment = new FeedOrgFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileOrgFragment();
                            break;
                    }

                    getSupportFragmentManager()

                            .beginTransaction()

                            .replace(R.id.fragment_layout, selectedFragment)

                            .commit();

                    return true;
                }
            };

}