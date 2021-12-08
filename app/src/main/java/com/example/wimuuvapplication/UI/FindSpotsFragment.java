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
    public ArrayList<String> spotsId;
    public ArrayList<String> spotsName;
    public ArrayAdapter<String> adapterSpots;
    JSONArray objspots;
    private FragmentFindSpotsBinding binding;
    private ListView listViewSpots;
    public ArrayList<String> spotsLong;
    public ArrayList<String> spotsLat;
    public ArrayList<String> spotsDescription;
    public ArrayList<String> spotsPhotoId;


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
                    double spotId1 = obj.getDouble("id");
                    String spot_name1 = obj.getString("name");
                    String spot_long = obj.getString("long");
                    String spot_lat = obj.getString("spot_lat");
                    String spot_photo = obj.getString("spot_photo");
                    spots.add(String.format("%s - Rate: %.2f", spotId1, spot_name1));
                    spotsId.add(obj.getString("id"));
                    spotsName.add(obj.getString("spot_name"));
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