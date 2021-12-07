package com.example.wimuuvapplication.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;



public class FindEventsFragment extends Fragment {

    public ArrayList<String> events;
    public ArrayList<String> eventsId;
    public ArrayList<String> eventsName;
    public ArrayAdapter<String> adapterEvents;
    JSONArray objevents;


    

   
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FindEventsFragment.inflate(inflater, container, false);
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
        eventsId = new ArrayList<>();
        eventsName = new ArrayList<>();
        if(objevents != null) {
            for(int i = 0; i < objevents.length(); i++) {
                try {
                    obj = objevents.getJSONObject(i);
                    double routesAvg = obj.getDouble("rtAvg");
                    String routeName = obj.getString("rtName");
                    events.add(String.format("%s - Rate: %.2f", routeName, routesAvg));
                    eventsId.add(obj.getString("id"));
                    eventsName.add(obj.getString("rtName"));
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
    */
}