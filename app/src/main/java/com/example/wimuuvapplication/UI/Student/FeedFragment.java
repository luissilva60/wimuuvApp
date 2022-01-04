package com.example.wimuuvapplication.UI.Student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.Org.CreateEventOrg;
import com.example.wimuuvapplication.UI.Student.FeedDetails;
import com.example.wimuuvapplication.databinding.FragmentFeedBinding;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
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


    private ImageButton filters;

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
        filters = binding.filtrosfeed;
        JSONArrayDownloader task = new JSONArrayDownloader();

        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filters = new Intent(getContext(), FilterActivity.class);
                startActivity(filters);
            }
        });



        try {
            objevents = task.execute("https://wimuuv.herokuapp.com/api/events").get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            objevents = null;
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
                    events.add(String.format("%s : \n- %s \n- %s \n- %s - %s \n\n\t\t\t Clique para ver mais detalhes",eventname1, eventdescription1,eventdate1,eventstartime1,eventendtime1));

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
        //adapterEvents = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, events);
        //listViewEvents.setAdapter(adapterEvents);
        listViewEvents.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, events) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                int textColor = textView.getText().toString().equals(events) ? R.color.white : R.color.white;
                textView.setTextColor(getContext().getResources().getColor(textColor));

                return textView;
            }
        });
        createListViewClickItemEvent(listViewEvents, events, eventId,eventName,eventDescription,eventDate,eventStartTime,eventEndTime,eventTypeId,eventStateId,eventOrgId,eventSpotId);
    }


    private void createListViewClickItemEvent(ListView listViewEvents, ArrayList<String> events, ArrayList<String> eventId, ArrayList<String> eventName, ArrayList<String> eventDescription, ArrayList<String> eventDate, ArrayList<String> eventStartTime,ArrayList<String> eventEndTime, ArrayList<Integer> eventTypeId, ArrayList<Integer> eventStateId, ArrayList<Integer> eventOrgId, ArrayList<Integer> eventSpotId) {
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                Intent intent = new Intent(getContext(), FeedDetails.class);
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
                intent.putExtra("id", ID_EVENT);
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
                //adapterEvents.notifyDataSetInvalidated();
                //intent.putExtras(result);
                startActivity(intent);
                //intent.putExtra("id",eventId.get(i));
                //intent.putExtra("name",eventName.get(i));
            }
        });
    }

}