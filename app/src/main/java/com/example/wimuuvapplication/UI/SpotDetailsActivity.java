package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.wimuuvapplication.R;

public class SpotDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        Log.e("asdasdasdasdas", "Spot id in spot details: " + id);
        setContentView(R.layout.activity_spot_details);
    }
}