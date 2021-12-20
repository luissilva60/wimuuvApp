package com.example.wimuuvapplication.UI.Org;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.Student.Register;
import com.example.wimuuvapplication.downloaders.GetPersons;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.example.wimuuvapplication.downloaders.PostData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CreateEventOrg extends AppCompatActivity {
    EditText eventName,desc,eventDate,starttime,endtime,duration,capacity;
    Button createEvent;
    String postEventDate;
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
    JSONArray events = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_org);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        toolbar.setTitle("Criar Evento");
        setSupportActionBar(toolbar);
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

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEventOrg.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        postEventDate = year + "-" + month + "-" + day;
                        String date = year + "/" + month + "/" + day;
                        eventDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorStateId = "3";
                String valorphotos = "1";
                String valorrating = "2";
                String spotId2 = new String();
                String typeId2 = new String();
                GetPersons getEvents = new GetPersons();
                Intent i2 = new Intent(getApplicationContext(),FeedOrgFragment.class);
                try {
                    events = getEvents.execute("https://wimuuv.herokuapp.com/api/events").get();
                    JSONObject aux = new JSONObject(events.get(0).toString());

                    if (eventName.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        eventName.setHintTextColor(Color.RED);
                    }
                    if (eventDate.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        eventDate.setHintTextColor(Color.RED);
                    }
                    if (starttime.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        starttime.setHintTextColor(Color.RED);
                    }
                    if (endtime.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        endtime.setHintTextColor(Color.RED);
                    }
                    if (duration.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        duration.setHintTextColor(Color.RED);
                    }
                    if (capacity.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        capacity.setHintTextColor(Color.RED);
                    }
                    if(typeS.getSelectedItemId() == 0){
                        typeId2 = String.valueOf(typeId.get(0));
                    }
                    if(typeS.getSelectedItemId() == 1){
                        typeId2 = String.valueOf(typeId.get(1));
                    }
                    if(typeS.getSelectedItemId() == 2){
                        typeId2 = String.valueOf(typeId.get(2));
                    }
                    if(typeS.getSelectedItemId() == 3) {
                        typeId2 = String.valueOf(typeId.get(3));
                    }
                    if(spotS.getSelectedItemId() == 0){
                        spotId2 = String.valueOf(spotId.get(0));
                    }
                    if(spotS.getSelectedItemId() == 1){
                        spotId2 = String.valueOf(spotId.get(1));
                    }
                    else {
                        Map<String, String> postData = new HashMap<>();
                        postData.put("typeId", typeId2);
                        postData.put("date", eventDate.getText().toString());
                        postData.put("description", desc.getText().toString());
                        postData.put("starttime", starttime.getText().toString());
                        postData.put("endtime", endtime.getText().toString());
                        postData.put("orgId", OrgLoginActivity.ORG_ID);
                        postData.put("spotId",spotId2);
                        postData.put("stateId",valorStateId);
                        postData.put("event_name", eventName.getText().toString());
                        postData.put("photos",valorphotos);
                        postData.put("rating",valorrating);


                        PostData task3 = new PostData(postData);
                        task3.execute("https://wimuuv.herokuapp.com/api/events/new");


                        Log.e("Create Event Activity", ""+ postData.toString());
                        startActivity(i2);

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}