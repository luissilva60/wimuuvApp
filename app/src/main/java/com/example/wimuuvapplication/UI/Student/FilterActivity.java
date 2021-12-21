package com.example.wimuuvapplication.UI.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FilterActivity extends AppCompatActivity {
    private JSONArray typeEvents;
    private JSONArray spotEvents;
    private ArrayList<String> typeNames;
    private ArrayList<String> type1;
    private ArrayList<Integer> typeId;
    private ArrayList<String> spotNames;
    private ArrayList<String> spot1;
    private ArrayList<Integer> spotId;
    private ArrayAdapter<String> adapterType;
    private ArrayAdapter<String> adapterSpot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Filtros");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        JSONArrayDownloader task = new JSONArrayDownloader();
        JSONArrayDownloader task2 = new JSONArrayDownloader();

        Spinner spotS = (Spinner) findViewById(R.id.FiltersSpinnerSpot);
        Spinner typeS = (Spinner) findViewById(R.id.FiltersSpinnerType);

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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}