package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.wimuuvapplication.R;

public class SpotDetailsActivity extends AppCompatActivity {

    private ListView listViewEventsfSpot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        Log.e("asdasdasdasdas", "Spot id in spot details: " + id);
        setContentView(R.layout.activity_spot_details);
        listViewEventsfSpot = findViewById(R.id.listEventsfromSpot);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Eventos neste Spot");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}