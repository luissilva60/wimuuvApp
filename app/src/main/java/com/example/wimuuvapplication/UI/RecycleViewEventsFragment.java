package com.example.wimuuvapplication.UI;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.databinding.FragmentFeedBinding;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class RecycleViewEventsFragment extends Fragment {

    private FragmentFeedBinding binding;
    private JSONArray eventsArray;
    private ArrayList<String> eventId;
    private ArrayList<String> eventName;
    private ArrayList<String> eventDescription;
    private RecyclerViewClickListener listener;

    public RecycleViewEventsFragment(JSONArray eventsArray, ArrayList<String> eventId,ArrayList<String> eventName, ArrayList<String> eventDescription, RecyclerViewClickListener listener) {
        this.eventsArray = eventsArray;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.listener = listener;
    }

    public RecycleViewEventsFragment() {
        // Required empty public constructor
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameTxt;

        public MyViewHolder(final View view){
            super(view);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
    public static RecycleViewEventsFragment newInstance(String param1, String param2) {
        RecycleViewEventsFragment fragment = new RecycleViewEventsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJSON(FeedFragment.ID_EVENT,"event");

    }

    public void getJSON(String id, String name){
        JSONArrayDownloader task = new JSONArrayDownloader();
        String url = "https://wimuuv.herokuapp.com/api/events/" + eventId;
        try {
            eventsArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            eventsArray = null;
        }
        JSONObject obj;
        eventId = new ArrayList<>();
        eventName = new ArrayList<>();
        eventDescription = new ArrayList<>();
        if (eventsArray != null) {
            for (int i = 0; i < eventsArray.length(); i++) {
                try {
                    obj = eventsArray.getJSONObject(i);
                    eventName.add(obj.getString("event_name"));
                    eventId.add(obj.getString("id"));
                    eventDescription.add(obj.getString("description"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);

    }

}