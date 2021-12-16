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
    private ArrayList<Integer> eventTypeId;
    private ArrayList<String> eventDate;
    private ArrayList<String> eventStartTime;
    private ArrayList<String> eventEndTime;
    private ArrayList<Time> eventDuration;
    private ArrayList<Integer> eventsOrgId;
    private ArrayList<Integer> eventSpotId;
    private ArrayList<Integer> eventCapacity;
    private ArrayList<Integer> eventPhotosId;
    private ArrayList<Integer> eventStateId;
    private ArrayList<Integer> eventRateId;
    private ArrayAdapter<String> adapterEvents;
    private String spotname;
    private String typename;
    private JSONObject spot;
    private JSONObject type;
    private JSONArray objevents;
    private ListView listViewEvents;
    private FragmentFeedBinding binding;
    public static String ID_EVENT;
    public static String EVENT_NAME;
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
        JSONArrayDownloader task4 = new JSONArrayDownloader();
        JSONArrayDownloader task5 = new JSONArrayDownloader();
        JSONArrayDownloader task6 = new JSONArrayDownloader();

        try {
            objevents = task.execute("https://wimuuv.herokuapp.com/api/events").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*try {
            type = task2.execute("https://wimuuv.herokuapp.com/api/type/"+ eventTypeId).get();
            typename = type.getString("event");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        /*try {
            spot = task3.execute("https://wimuuv.herokuapp.com/api/spot/"+ eventSpotId).get();
            spotname = spot.getString("name");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/




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
        eventsOrgId = new ArrayList<>();
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
        createListViewClickItemEvent(listViewEvents, events, eventId, eventName);
    }

    private void createListViewClickItemEvent(ListView listViewEvents, ArrayList<String> events, ArrayList<String> eventId, ArrayList<String> eventName) {
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(),FeedDetails.class);
                //Bundle result = new Bundle();
                ID_EVENT = eventId.get(i);
                EVENT_NAME = eventName.get(i);
                intent.putExtra("name",EVENT_NAME);

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