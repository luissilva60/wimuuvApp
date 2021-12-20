package com.example.wimuuvapplication.UI.Org;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CreateEventOrg extends AppCompatActivity {
    EditText eventName,desc,eventDate,starttime,endtime,duration,capacity;
    Button createEvent;
    JSONArray typeEvents;
    JSONArray spotEvents;
    ArrayList<String> typeNames;
    ArrayList<String> type1;
    ArrayList<Integer> typeId;
    ArrayList<String> spotNames;
    ArrayList<String> spot1;
    ArrayList<Integer> spotId;
    ArrayAdapter<String> adapterType;
    ArrayAdapter<String> adapterSpot;
    TextView type,spot,textCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_org);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Criar Evento");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventName = findViewById(R.id.editTextTextEventName);
        desc = findViewById(R.id.editTextTextEmailAddress2);
        eventDate = findViewById(R.id.editTextDate);
        starttime = findViewById(R.id.editTextTextStarttime);
        endtime = findViewById(R.id.editTextTextEndtime);
        duration = findViewById(R.id.editTextTextDuration);
        capacity = findViewById(R.id.editTextTextCapacity);

        Spinner spotS = (Spinner) findViewById(R.id.spinnerSpot);
        Spinner typeS = (Spinner) findViewById(R.id.spinnerType);
        createEvent = findViewById(R.id.buttonCreateEvent);

        JSONArrayDownloader task = new JSONArrayDownloader();
        JSONArrayDownloader task2 = new JSONArrayDownloader();

        try {
            typeEvents = task.execute("https://wimuuv.herokuapp.com/api/type/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject obj;
        type1 = new ArrayList<>();
        typeNames = new ArrayList<>();
        typeId = new ArrayList<>();
        if(typeEvents != null) {
            for (int i = 0; i < typeEvents.length();i++){
                try{
                    obj = typeEvents.getJSONObject(i);
                    String typeName = obj.getString("event");

                    typeId.add(obj.getInt("id"));
                    typeNames.add(obj.getString("event"));
                    type1.add(String.format("%s",typeName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        adapterType = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,type1);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeS.setAdapter(adapterType);

        try {
            spotEvents = task2.execute("https://wimuuv.herokuapp.com/api/spot/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject obj2;
        spot1 = new ArrayList<>();
        spotNames = new ArrayList<>();
        spotId = new ArrayList<>();
        if(spotEvents != null) {
            for (int i = 0; i < spotEvents.length();i++){
                try{
                    obj2 = spotEvents.getJSONObject(i);
                    String spotName = obj2.getString("name");

                    spotId.add(obj2.getInt("id"));
                    spotNames.add(obj2.getString("name"));
                    spot1.add(String.format("%s",spotName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        adapterSpot = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,spot1);
        adapterSpot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spotS.setAdapter(adapterSpot);



    }
}