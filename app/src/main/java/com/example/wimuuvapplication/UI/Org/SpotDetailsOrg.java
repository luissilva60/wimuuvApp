package com.example.wimuuvapplication.UI.Org;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.Student.FeedDetails;
import com.example.wimuuvapplication.UI.Student.MapsFragment;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotDetailsOrg extends AppCompatActivity {

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
    private ArrayAdapter<String> adapterEvents;
    public static String spotname;
    public static String statename;
    public static String typename;
    public static String orgname;
    private JSONObject spot;
    private JSONObject type;
    private JSONObject state;
    private JSONObject org;
    private JSONArray objevents;
    public static String ID_EVENT;
    public static String EVENT_ORG_ID;
    public static String EVENT_NAME;
    public static String EVENT_DESC;
    public static String EVENT_STARTTIME;
    public static String EVENT_ENDTIME;
    public static String EVENT_DATE;
    public static String EVENT_SPOT;
    public static String EVENT_STATE;
    public static String EVENT_TYPE;
    public static String EVENT_ORG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_details_org);
        listViewEventsfSpot = findViewById(R.id.listEventsfromSpot);
        events = new ArrayList<String>();
        JSONObject obj;
        JSONArrayDownloader task = new JSONArrayDownloader();

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Eventos neste Spot: ");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            objevents = task.execute("https://wimuuv.herokuapp.com/api/events/spot/" + MapsOrgFragment.EVENT_SPOT_ID).get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }




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
                    String eventname1 = obj.getString("event_name");
                    String eventdescription1 = obj.getString("description");
                    String eventdate1 = obj.getString("date");
                    String eventstartime1 = obj.getString("starttime");
                    String eventendtime1 = obj.getString("endtime");

                    eventTypeId.add(obj.getInt("typeId"));
                    eventStateId.add(obj.getInt("stateId"));
                    eventSpotId.add(obj.getInt("spotId"));
                    eventOrgId.add(obj.getInt("orgId"));
                    eventId.add(obj.getString("id"));
                    eventName.add(obj.getString("event_name"));
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
        adapterEvents = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, events);
        listViewEventsfSpot.setAdapter(adapterEvents);
        createListViewClickItemEvent(listViewEventsfSpot, events, eventId, eventName, eventDescription, eventDate, eventStartTime, eventEndTime, eventTypeId, eventStateId, eventOrgId, eventSpotId);
    }


    private void createListViewClickItemEvent(ListView listViewEventsfSpot, ArrayList<String> events, ArrayList<String> eventId, ArrayList<String> eventName, ArrayList<String> eventDescription, ArrayList<String> eventDate, ArrayList<String> eventStartTime, ArrayList<String> eventEndTime, ArrayList<Integer> eventTypeId, ArrayList<Integer> eventStateId, ArrayList<Integer> eventOrgId, ArrayList<Integer> eventSpotId) {
        listViewEventsfSpot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JSONObjDownloader task2 = new JSONObjDownloader();
                JSONObjDownloader task3 = new JSONObjDownloader();
                JSONObjDownloader task4 = new JSONObjDownloader();
                JSONObjDownloader task5 = new JSONObjDownloader();
                try {
                    type = task2.execute("https://wimuuv.herokuapp.com/api/type/" + eventTypeId.get(i)).get();
                    typename = type.getString("event");

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    org = task3.execute("https://wimuuv.herokuapp.com/api/orgs/" + eventOrgId.get(i)).get();
                    orgname = org.getString("name");

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    spot = task4.execute("https://wimuuv.herokuapp.com/api/spot/" + eventSpotId.get(i)).get();
                    spotname = spot.getString("name");

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    state = task5.execute("https://wimuuv.herokuapp.com/api/state/" + eventStateId.get(i)).get();
                    statename = state.getString("event");

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent2 = new Intent(getApplicationContext(), FeedDetailsOrg.class);

                ID_EVENT = eventId.get(i);
                EVENT_ORG_ID = eventOrgId.get(i).toString();
                EVENT_NAME = eventName.get(i);
                EVENT_DESC = eventDescription.get(i);
                EVENT_STARTTIME = eventStartTime.get(i);
                EVENT_ENDTIME = eventEndTime.get(i);
                EVENT_DATE = eventDate.get(i);
                EVENT_SPOT = spotname;
                EVENT_STATE = statename;
                EVENT_TYPE = typename;
                EVENT_ORG = orgname;

                intent2.putExtra("id", EVENT_ORG_ID);
                intent2.putExtra("name", EVENT_NAME);
                intent2.putExtra("desc", EVENT_DESC);
                intent2.putExtra("starttime", EVENT_STARTTIME);
                intent2.putExtra("endtime", EVENT_ENDTIME);
                intent2.putExtra("date", EVENT_DATE);
                intent2.putExtra("spot", EVENT_SPOT);
                intent2.putExtra("state", EVENT_STATE);
                intent2.putExtra("type", EVENT_TYPE);
                intent2.putExtra("org", EVENT_ORG);


                startActivity(intent2);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}