package com.example.wimuuvapplication.UI;

import android.annotation.SuppressLint;
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
import java.util.concurrent.ExecutionException;

public class FeedFragment extends Fragment {

    public ArrayList<String> events;
    public ArrayList<String> eventId;
    public ArrayList<String> eventName;
    public ArrayList<String> eventDescription;
    public ArrayList<Integer> eventTypeId;
    public ArrayList<String> eventDate;
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
    String spotname;
    JSONObject spot;
    JSONObject spot2;
    JSONArray objevents;
    private ListView listViewEvents;
    private FragmentFeedBinding binding;

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
            spot = task2.execute("https://wimuuv.herokuapp.com/api/spot/"+ eventSpotId).get();
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
                    String eventname1 = obj.getString("event_name");
                    String eventdescription1 = obj.getString("description");
                    eventName.add(obj.getString("event_name"));
                    eventDescription.add(obj.getString("description"));
                    events.add(String.format("%s - %s ",eventname1, eventdescription1));

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            InitializeAdapter();
        }
        Log.e("Array List", events.toString());

        return root;
    }
    public void InitializeAdapter() {
        adapterEvents = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, events);
        listViewEvents.setAdapter(adapterEvents);
        createListViewClickItemEvent(listViewEvents,events,eventId,eventName,eventDescription,eventTypeId,eventDate,eventStartTime,eventEndTime,eventDuration,eventsOrgId,eventSpotId,eventCapacity,eventPhotosId,eventStateId,eventRateId);
    }

    private void createListViewClickItemEvent(ListView listViewEvents, final ArrayList<String> item, final ArrayList<String> eventId, final ArrayList<String> eventName, final ArrayList<String> eventDescription, final ArrayList<Integer> eventTypeId, final ArrayList<String> eventDate, final ArrayList<Time> eventStartTime, final ArrayList<Time> eventEndTime, final ArrayList<Time> eventDuration, final ArrayList<Integer> eventsOrgId, final ArrayList<Integer> eventSpotId, final ArrayList<Integer> eventCapacity, final ArrayList<Integer> eventPhotosId, final ArrayList<Integer> eventStateId, final ArrayList<Integer> eventRateId) {

        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Log.e("INFO",item.get(i));
                Log.e("INFO", eventId.get(i));
                Bundle result = new Bundle();
                result.putString("id",eventId.get(i));
                result.putString("name",eventName.get(i));
                /*result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));
                result.putString("eventName",eventName.get(i));*/

                getParentFragmentManager().setFragmentResult("event", result);

                Navigation.findNavController(view)
                        .navigate(R.id.listEvents);

            }
        });
    }

}