package com.example.wimuuvapplication.UI.Org;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.Student.QRCodeEventActivity;

import org.json.JSONArray;

public class FeedDetailsOrg extends AppCompatActivity {

    private JSONArray test;
    private TextView name, desc, spot, org, starttime, endtime, date, type,state;
    private Button qrcode;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details_org);
        qrcode = findViewById(R.id.lerQRCODE);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.e("Info:", "id" + id );
        String eventName = intent.getStringExtra("name");
        String eventDesc = intent.getStringExtra("desc");
        String eventSpot = intent.getStringExtra("spot");
        String eventOrg = intent.getStringExtra("org");
        String eventStarttime = intent.getStringExtra("starttime");
        String eventEndtime = intent.getStringExtra("endtime");
        String eventDate = intent.getStringExtra("date");
        String eventType = intent.getStringExtra("type");
        String eventState = intent.getStringExtra("state");



        name = (TextView)findViewById(R.id.textViewEventNameOrg);
        desc = (TextView)findViewById(R.id.textViewEventDescriptionOrg);
        spot = (TextView)findViewById(R.id.textViewEventSpotOrg);
        org = (TextView)findViewById(R.id.textViewEventOrgOrg);
        starttime = (TextView)findViewById(R.id.textViewEventStarttimeOrg);
        endtime = (TextView)findViewById(R.id.textViewEventEndtimeOrg);
        date = (TextView)findViewById(R.id.textViewEventDateOrg);
        type = (TextView)findViewById(R.id.textViewEventTypeOrg);
        state = (TextView)findViewById(R.id.textViewEventStateOrg);


        name.setText("Nome: "+ eventName);
        desc.setText("Desc: "+ eventDesc);
        spot.setText("Spot: "+ eventSpot);
        org.setText("Org: "+ eventOrg);
        starttime.setText("Start: "+ eventStarttime);
        endtime.setText("End: "+ eventEndtime);
        date.setText("Data: "+ eventDate);
        type.setText("Tipo: "+ eventType);
        state.setText("Estado: "+ eventState);



        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Detalhes do Evento");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRCodeEventActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}