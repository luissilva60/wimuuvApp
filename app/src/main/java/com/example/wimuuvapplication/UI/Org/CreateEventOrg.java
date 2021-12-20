package com.example.wimuuvapplication.UI.Org;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wimuuvapplication.R;

public class CreateEventOrg extends AppCompatActivity {
    EditText eventName,desc,eventDate,starttime,endtime,duration,capacity;
    Button createEvent;
    TextView type,spot,textCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_org);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Sign Up");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventName = findViewById(R.id.editTextTextEventName);
        desc = findViewById(R.id.editTextTextEmailAddress2);
        eventDate = findViewById(R.id.editTextDate);
        starttime = findViewById(R.id.editTextTextStarttime);
        endtime = findViewById(R.id.editTextTextEndtime);

        //Spinner gender = (Spinner) findViewById(R.id.spinnerGender);
        //Spinner curso = (Spinner) findViewById(R.id.spinnerCurso);
        createEvent = findViewById(R.id.buttonCreateEvent);
    }
}