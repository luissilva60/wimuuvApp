package com.example.wimuuvapplication.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import com.example.wimuuvapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class FeedDetails extends AppCompatActivity {

    JSONArray test;
    TextView name;
    TextView id;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);

        Intent intent = getIntent();

        String eventName = intent.getStringExtra("name");
        name = (TextView)findViewById(R.id.textViewEventName);
        name.setText("Nome: "+ eventName);
        //Bundle bundle = getIntent().getExtras();
        //if(bundle!=null){
        //name.setText("Nome do Evento:  "+ bundle.getString("name"));
        //}
        //bundle.getString("id");
        //bundle.getString("name");
        //Log.e("INFO",bundle.getString("name"));
        //name.setText(bundle.getString("name"));



        //name.setText("Nome: " + bundle.getString("name"));





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