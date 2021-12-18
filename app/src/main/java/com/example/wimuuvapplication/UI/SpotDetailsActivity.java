package com.example.wimuuvapplication.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.databinding.ActivitySpotDetailsBinding;
import com.example.wimuuvapplication.databinding.FragmentFeedBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SpotDetailsActivity extends AppCompatActivity {

    private ActivitySpotDetailsBinding binding;
    private ListView listViewEventsfSpot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_details);
        listViewEventsfSpot = findViewById(R.id.listEventsfromSpot);
        binding = ActivitySpotDetailsBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        listViewEventsfSpot = binding.listEventsfromSpot;
        Intent intent = getIntent();
        String id = intent.getStringExtra("spotid");

        //Log.e("asdasdasdasdas", "Spot id in spot details: " + id);
        /*try {
            objevents = task.execute("https://wimuuv.herokuapp.com/api/spot/" + id + "/events").get();
            for (int i = 0; i < objevents.length(); i++){
                JSONObject object = objevents.getJSONObject(i);
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Eventos neste Spot");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
