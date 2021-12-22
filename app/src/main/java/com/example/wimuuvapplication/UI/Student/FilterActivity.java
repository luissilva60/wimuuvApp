package com.example.wimuuvapplication.UI.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

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
    private Button filtrar;
    public static String SPOT_ID = new String();
    public static String TYPE_ID = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Filtros");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String blank = "";
        String zero = "0";
        JSONArrayDownloader task = new JSONArrayDownloader();
        JSONArrayDownloader task2 = new JSONArrayDownloader();
        filtrar = findViewById(R.id.button5);
        Spinner spotS = (Spinner) findViewById(R.id.FiltersSpinnerSpot);
        Spinner typeS = (Spinner) findViewById(R.id.FiltersSpinnerType);


        try {
            typeEvents = task.execute("https://wimuuv.herokuapp.com/api/type/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject obj;
        type1 = new ArrayList<>();
        type1.add(blank);
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
        spot1.add(blank);
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

        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FilteredEvents.class);
                try {

                    for (int position = 0; position < 4; position++) {
                        if (typeS.getSelectedItemId() == position){
                            TYPE_ID = String.valueOf(typeId.get(position)-1);
                        }
                        for (int p = 0; p < 2; p++)
                            if (spotS.getSelectedItemId() == p){
                                SPOT_ID = String.valueOf(spotId.get(p)-1);
                            }
                        if (TYPE_ID.isEmpty() && SPOT_ID.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Insira os filtros", Toast.LENGTH_SHORT).show();
                        }
                        if (TYPE_ID.isEmpty()){
                           TYPE_ID = zero;

                        }
                        if (SPOT_ID.isEmpty()){
                            SPOT_ID = zero;
                        }
                    }
                    Log.e("typeID : ",""+ TYPE_ID);
                    Log.e("spotID : ",""+ SPOT_ID);
                    startActivity(i);
                }catch (Exception e) {
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