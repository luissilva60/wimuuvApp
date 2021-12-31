package com.example.wimuuvapplication.UI.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HistoricoDeEventosActivity extends AppCompatActivity {

    private ListView listViewEventsfSpot;
    private ArrayList<String> events;
    private ArrayList<String> eventId;
    private ArrayList<String> eventName;
    private ArrayList<String> eventDescription;
    private ArrayList<String> eventDate;
    private ArrayList<String> eventStartTime;
    private ArrayList<String> eventEndTime;
    private ArrayList<Time> eventDuration;
    private ArrayList<Integer> eventOrgId;
    private ArrayList<Integer> eventSpotId;
    private ArrayList<Integer> eventCapacity;
    private ArrayList<Integer> eventPhotosId;
    private ArrayList<Integer> eventStateId;
    private ArrayList<Integer> eventRateId;
    private ArrayList<Integer> eventTypeId;
    private JSONArray objevents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_de_eventos);
        getIntent();

        listViewEventsfSpot = findViewById(R.id.listhistorico);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Histórico");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        JSONArrayDownloader task = new JSONArrayDownloader();

        try {
            objevents = task.execute("https://wimuuv.herokuapp.com/api/events/historico/" + MainActivity.USER_ID).get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject obj;
        events = new ArrayList<>();
        eventId = new ArrayList<>();
        eventName = new ArrayList<>();
        eventDescription = new ArrayList<>();
        eventTypeId = new ArrayList<>();
        eventDate = new ArrayList<>();
        eventStartTime = new ArrayList<>();
        eventEndTime = new ArrayList<>();
        eventDuration = new ArrayList<>();
        eventOrgId = new ArrayList<>();
        eventSpotId = new ArrayList<>();
        eventCapacity = new ArrayList<>();
        eventPhotosId = new ArrayList<>();
        eventStateId = new ArrayList<>();
        eventRateId = new ArrayList<>();
        if (objevents != null) {
            for (int i = 0; i < objevents.length(); i++) {
                try {
                    obj = objevents.getJSONObject(i);
                    String eventname1 = obj.getString("name");
                    String eventdescription1 = obj.getString("description");
                    String eventdate1 = obj.getString("date");
                    String eventstartime1 = obj.getString("starttime");
                    String eventendtime1 = obj.getString("endtime");

                    eventTypeId.add(obj.getInt("typeId"));
                    eventStateId.add(obj.getInt("stateId"));
                    eventSpotId.add(obj.getInt("spotId"));
                    eventOrgId.add(obj.getInt("orgId"));
                    eventId.add(obj.getString("id"));
                    eventName.add(obj.getString("name"));
                    eventDescription.add(obj.getString("description"));
                    eventDate.add(obj.getString("date"));
                    eventStartTime.add(obj.getString("starttime"));
                    eventEndTime.add(obj.getString("endtime"));
                    events.add(String.format("%s - \n- %s \n- %s \n- %s - %s \n\t\t\t Clique para ver mais detalhes", eventname1, eventdescription1, eventdate1, eventstartime1, eventendtime1));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("Array List", events.toString());
            InitializeAdapter();
        }

    }

    public void InitializeAdapter() {
        listViewEventsfSpot.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, events) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                int textColor = textView.getText().toString().equals(events) ? R.color.white : R.color.white;
                textView.setTextColor(getContext().getResources().getColor(textColor));

                return textView;
            }
        });
        createListViewClickItemEvent(listViewEventsfSpot, events, eventId, eventName, eventDescription, eventDate, eventStartTime, eventEndTime, eventTypeId, eventStateId, eventOrgId, eventSpotId);
    }

    private void createListViewClickItemEvent(ListView listViewEventsfSpot, ArrayList<String> events, ArrayList<String> eventId, ArrayList<String> eventName, ArrayList<String> eventDescription, ArrayList<String> eventDate, ArrayList<String> eventStartTime, ArrayList<String> eventEndTime, ArrayList<Integer> eventTypeId, ArrayList<Integer> eventStateId, ArrayList<Integer> eventOrgId, ArrayList<Integer> eventSpotId) {
        listViewEventsfSpot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}