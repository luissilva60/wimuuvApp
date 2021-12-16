package com.example.wimuuvapplication.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.databinding.FragmentFeedBinding;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FeedFragment extends Fragment {


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
    public static int eventTypeId1;
    public static int eventSpotId1;
    public static int eventStateId1;
    public static int eventOrgId1;
    private JSONArray objevents;
    private JSONObject event;
    private ListView listViewEvents;
    private FragmentFeedBinding binding;
    public static String ID_EVENT;
    public static String EVENT_NAME;
    public static String EVENT_DESC;
    public static String EVENT_STARTTIME;
    public static String EVENT_ENDTIME;
    public static String EVENT_DATE;
    public static String EVENT_SPOT;
    public static String EVENT_STATE;
    public static String EVENT_TYPE;
    public static String EVENT_ORG;
    private int id;
    public static JSONArray test;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listViewEvents = binding.listEvents;
        JSONArrayDownloader task = new JSONArrayDownloader();
        JSONObjDownloader task2 = new JSONObjDownloader();
        JSONObjDownloader task3 = new JSONObjDownloader();
        JSONObjDownloader task4 = new JSONObjDownloader();
        JSONObjDownloader task5 = new JSONObjDownloader();


        try {
            objevents = task.execute("https://wimuuv.herokuapp.com/api/events").get();
            for (int i = 0; i < objevents.length(); i++){
                JSONObject object = objevents.getJSONObject(i);
                    eventTypeId1 = object.getInt("typeId");
                    eventStateId1 = object.getInt("stateId");
                    eventSpotId1 = object.getInt("spotId");
                    eventOrgId1 = object.getInt("orgId");
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /*try {
            event = task4.execute("https://wimuuv.herokuapp.com/api/events").get();
            eventTypeId1 = event.getInt("typeId");
            eventStateId1 = event.getInt("stateId");
            eventSpotId1 = event.getInt("spotId");
            eventOrgId1 = event.getInt("orgId");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        try {
            type = task2.execute("https://wimuuv.herokuapp.com/api/type/"+ eventTypeId1).get();
            typename = type.getString("event");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            org = task3.execute("https://wimuuv.herokuapp.com/api/orgs/" + eventOrgId1).get();
            orgname = org.getString("name");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            spot = task4.execute("https://wimuuv.herokuapp.com/api/spot/"+ eventSpotId1).get();
            spotname = spot.getString("name");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            state = task5.execute("https://wimuuv.herokuapp.com/api/state/"+ eventStateId1).get();
            statename = state.getString("event");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
        if(objevents != null) {
            for(int i = 0; i < objevents.length(); i++) {
                try {
                    obj = objevents.getJSONObject(i);
                    test = objevents;
                    String eventname1 = obj.getString("event_name");
                    String eventdescription1 = obj.getString("description");
                    String eventdate1 = obj.getString("date");
                    String eventstartime1 = obj.getString("starttime");
                    String eventendtime1 = obj.getString("endtime");
                    if(obj.has("event")){
                        typename = obj.getString("event");
                    }
                    if(obj.has("name")){
                        spotname = obj.getString("name");
                    }
                    if(obj.has("event")){
                        statename = obj.getString("event");
                    }
                    if(obj.has("name")){
                        orgname = obj.getString("name");
                    }

                    eventId.add(obj.getString("id"));
                    eventName.add(obj.getString("event_name"));
                    eventDescription.add(obj.getString("description"));
                    eventDate.add(obj.getString("date"));
                    eventStartTime.add(obj.getString("starttime"));
                    eventEndTime.add(obj.getString("endtime"));
                    events.add(String.format("%s - \n- %s \n- %s \n- %s - %s \n\t\t\t Clique para ver mais detalhes",eventname1, eventdescription1,eventdate1,eventstartime1,eventendtime1));

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("Array List", events.toString());
            InitializeAdapter();
        }

        return root;
    }
    public void InitializeAdapter() {
        adapterEvents = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, events);
        listViewEvents.setAdapter(adapterEvents);
        createListViewClickItemEvent(listViewEvents, events, eventId, eventName,typename,spotname,statename,eventDescription,eventDate,eventStartTime,eventEndTime,orgname);
    }


    private void createListViewClickItemEvent(ListView listViewEvents, ArrayList<String> events, ArrayList<String> eventId, ArrayList<String> eventName, String typename, String spotname, String statename, ArrayList<String> eventDescription, ArrayList<String> eventDate, ArrayList<String> eventStartTime, ArrayList<String> eventEndTime, String orgname) {
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(),FeedDetails.class);
                //Bundle result = new Bundle();
                ID_EVENT = eventId.get(i);
                EVENT_NAME = eventName.get(i);
                EVENT_DESC = eventDescription.get(i);
                EVENT_STARTTIME = eventStartTime.get(i);
                EVENT_ENDTIME = eventEndTime.get(i);
                EVENT_DATE = eventDate.get(i);
                EVENT_SPOT = spotname;
                EVENT_STATE = statename;
                EVENT_TYPE = typename;
                EVENT_ORG = orgname;
                intent.putExtra("name",EVENT_NAME);
                intent.putExtra("desc",EVENT_DESC);
                intent.putExtra("starttime",EVENT_STARTTIME);
                intent.putExtra("endtime",EVENT_ENDTIME);
                intent.putExtra("date",EVENT_DATE);
                intent.putExtra("spot",EVENT_SPOT);
                intent.putExtra("state",EVENT_STATE);
                intent.putExtra("type",EVENT_TYPE);
                intent.putExtra("org",EVENT_ORG);

                //result.putString("id",eventId.get(i));
                //result.putString("name",eventName.get(i));
                //getParentFragmentManager().setFragmentResult("event", result);

                //intent.putExtras(result);
                startActivity(intent);
                //intent.putExtra("id",eventId.get(i));
                //intent.putExtra("name",eventName.get(i));
            }
        });
    }

}