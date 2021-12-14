package com.example.wimuuvapplication.UI;

import android.annotation.SuppressLint;
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
import android.widget.ListView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.databinding.FragmentFeedBinding;
import com.example.wimuuvapplication.databinding.FragmentFeedBindingImpl;
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
    public ArrayList<String> eventStartTime;
    public ArrayList<String> eventEndTime;
    public ArrayList<Time> eventDuration;
    public ArrayList<Integer> eventsOrgId;
    public ArrayList<Integer> eventSpotId;
    public ArrayList<Integer> eventCapacity;
    public ArrayList<Integer> eventPhotosId;
    public ArrayList<Integer> eventStateId;
    public ArrayList<Integer> eventRateId;
    public ArrayAdapter<String> adapterEvents;
    String spotname;
    String typename;
    JSONObject spot;
    JSONObject type;
    JSONArray objevents;
    private ListView listViewEvents;
    private FragmentFeedBinding binding;
    public static String ID_EVENT;
    private RecycleViewEventsFragment.RecyclerViewClickListener listener;

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
                    String eventname1 = obj.getString("event_name");
                    String eventdescription1 = obj.getString("description");
                    String eventdate1 = obj.getString("date");
                    String eventstartime1 = obj.getString("starttime");
                    String eventendtime1 = obj.getString("endtime");

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
        createListViewClickItemEvent(listViewEvents,events,eventId,eventName);
    }

    private void createListViewClickItemEvent(ListView list, final ArrayList<String> item, final ArrayList<String> id, final ArrayList<String> name) {

        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("INFO",item.get(i));
                Log.e("INFO",id.get(i));
                Bundle result = new Bundle();
                ID_EVENT = id.get(i);
                result.putString("id",id.get(i));
                result.putString("name",name.get(i));


                getParentFragmentManager().setFragmentResult("event", result);

                Navigation.findNavController(view)
                        .navigate(R.id.item);

            }
        });
    }


}