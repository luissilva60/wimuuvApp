package com.example.wimuuvapplication.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.wimuuvapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class FeedDetails extends AppCompatActivity {

    JSONArray test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        test = FeedFragment.test;

        JSONObject sei;

        try {
            sei = test.getJSONObject(0);
            Log.e("jksydgfyuwgfjyewgfliewifuhewliuhfiweuhfiuewhf",""+sei.getString("id"));
            Log.e("jksydgfyuwgfjyewgfliewifuhewliuhfiweuhfiuewhf",""+test);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("jksydgfyuwgfjyewgfliewifuhewliuhfiweuhfiuewhf",""+test);
        Log.e("jksydgfyuwgfjyewgfliewifuhewliuhfiweuhfiuewhf","vlvkjdsnvukndsvnsdlnkv");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Detalhes do Evento");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}