package com.example.wimuuvapplication.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wimuuvapplication.databinding.FragmentFindSpotsBinding;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;



public class FindSpotsFragment extends Fragment {

    public ArrayList<String> spots;
    public ArrayList<Integer> spotsId;
    public ArrayList<String> spotsName;
    public ArrayAdapter<String> adapterSpots;
    JSONArray objspots;
    private FragmentFindSpotsBinding binding;
    private ListView listViewSpots;
    public ArrayList<Double> spotsLong;
    public ArrayList<Double> spotsLat;
    public ArrayList<String> spotsDescription;
    public ArrayList<Integer> spotsPhotoId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFindSpotsBinding.inflate(inflater, container, false);
        JSONArrayDownloader task = new JSONArrayDownloader();
        View root = binding.getRoot();

        try {
            objspots = task.execute("https://wimuuv.herokuapp.com/api/spots").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            objspots = null;
        }

        JSONObject obj;
        spots = new ArrayList<>();
        spotsId = new ArrayList<>();
        spotsName = new ArrayList<>();
        spotsLong = new ArrayList<>();
        spotsLat = new ArrayList<>();
        spotsDescription = new ArrayList<>();
        spotsPhotoId = new ArrayList<>();
        if(objspots != null) {
            for(int i = 0; i < objspots.length(); i++) {
                try {
                    obj = objspots.getJSONObject(i);
                    Integer spotId1 = obj.getInt("id");
                    String spotname1 = obj.getString("name");
                    Double spotlong1 = obj.getDouble("longi");
                    Double spotlat1 = obj.getDouble("lat");
                    Integer spotphoto1 = obj.getInt("photo");
                    String spotdescription1 = obj.getString("description");
                    spots.add(String.format("%s - Rate: %.2f", spotId1, spotname1, spotlong1, spotlat1, spotphoto1, spotdescription1));
                    spotsId.add(obj.getInt("id"));
                    spotsName.add(obj.getString("name"));
                    spotsLat.add(obj.getDouble("lat"));
                    spotsLong.add(obj.getDouble("longi"));
                    spotsDescription.add(obj.getString("description"));
                    spotsName.add(obj.getString("description"));
                    spotsPhotoId.add(obj.getInt("photo"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("Array List", spots.toString());
            InitalizeAdapter();
        }

        return root;

    }



    public void InitalizeAdapter() {
        adapterSpots = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spots);
        listViewSpots.setAdapter(adapterSpots);
    }



}