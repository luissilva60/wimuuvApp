package com.example.wimuuvapplication.UI;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;

import com.example.wimuuvapplication.databinding.FragmentFindEventsBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;



public class FindEventsFragment extends Fragment {

    public ArrayList<String> events;
    public ArrayList<Integer> eventId;
    public ArrayList<String> eventName;
    public ArrayList<String> eventDescription;
    public ArrayList<Integer> eventTypeId;
    public ArrayList<LocalDate> eventDate;
    public ArrayList<Time> eventStartTime;
    public ArrayList<Time> eventEndTime;
    public ArrayList<Time> eventDuration;
    public ArrayList<Integer> eventsOrgId;
    public ArrayList<Integer> eventSpotId;
    public ArrayList<Integer> eventCapacity;
    public ArrayList<Integer> eventPhotosId;
    public ArrayList<Integer> eventStateId;
    public ArrayList<Integer> eventRateId;


    public ArrayAdapter<String> adapterEvents;
    JSONArray objevents;

    private ListView listViewEvents;
    private FragmentFindEventsBinding binding;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFindEventsBinding.inflate(inflater, container, false);
        JSONArrayDownloader task = new JSONArrayDownloader();
        View root = binding.getRoot();

        try {
            objevents = task.execute("https://wimuuv.herokuapp.com/api/events").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            objevents = null;
        }

        JSONObject obj;
        events = new ArrayList<>();
        eventId = new ArrayList<>();
        eventName = new ArrayList<>();
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
                    Integer eventid1 = obj.getInt("id");
                    Integer eventdescription1 = obj.getInt("description");
                    Integer eventtypeId1 = obj.getInt("typeId");
                    Integer eventdate1 = obj.getInt("date");
                    Integer eventstarttime1 = obj.getInt("starttime");
                    Integer eventendtime1 = obj.getInt("endtime");
                    Integer eventduration1 = obj.getInt("duration");
                    Integer eventorgId1 = obj.getInt("orgId");
                    Integer eventspotId1 = obj.getInt("spotId");
                    Integer eventcapacity1 = obj.getInt("capacity");
                    Integer eventphotosId1 = obj.getInt("photosId");
                    Integer eventstateId1 = obj.getInt("stateId");
                    Integer eventrateId1 = obj.getInt("rateId");


                    events.add(String.format("%s - Rate: %.2f", eventid1,eventdescription1, eventtypeId1,eventdate1,
                            eventdate1 , eventstarttime1 ,eventendtime1, eventduration1, eventorgId1,
                            eventspotId1, eventcapacity1, eventphotosId1,eventstateId1 , eventrateId1 ));
                    eventId.add(obj.getInt("id"));
                    eventName.add(obj.getString("name"));
                    eventDescription.add(obj.getString("description"));
                    eventTypeId.add(obj.getInt("typeId"));
                    eventDate.add((LocalDate) obj.get("date"));
                    eventStartTime.add((Time) obj.get("starttime"));
                    eventEndTime.add((Time) obj.get("endtime"));
                    eventDuration.add((Time) obj.get("duration"));
                    eventsOrgId.add(obj.getInt("orgId"));
                    eventSpotId.add(obj.getInt("spotId"));
                    eventCapacity.add(obj.getInt("capacity"));
                    eventPhotosId.add(obj.getInt("photosId"));
                    eventStateId.add(obj.getInt("stateId"));
                    eventRateId.add(obj.getInt("rateId"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("Array List", events.toString());
            InitalizeAdapter();
        }

        return root;

    }



    public void InitalizeAdapter() {
        adapterEvents = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, events);
        listViewEvents.setAdapter(adapterEvents);
    }


}