package com.example.wimuuvapplication.UI.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.GetPersons;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.example.wimuuvapplication.downloaders.PostData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RateActivity extends AppCompatActivity {
    private EditText comment,rate;
    private Button avaliar;
    private JSONArray studentRate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        toolbar.setTitle("Rate");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        String eventId = intent.getStringExtra("id");

        comment = findViewById(R.id.editTextTextComment);
        rate = findViewById(R.id.editTextTextRate);
        avaliar = findViewById(R.id.buttonAvaliar);

        JSONArrayDownloader task = new JSONArrayDownloader();
        JSONArrayDownloader task2 = new JSONArrayDownloader();

        avaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPersons getRate = new GetPersons();
                Intent i2 = new Intent(getApplicationContext(),MainActivity2.class);
                try {
                    studentRate = getRate.execute("https://wimuuv.herokuapp.com/api/student_rate").get();
                    JSONObject aux = new JSONObject(studentRate.get(0).toString());
                    if (rate.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        rate.setHintTextColor(Color.RED);
                    }
                    else {
                        Map<String, String> postData = new HashMap<>();
                        postData.put("evRid", eventId);
                        postData.put("stuRid", MainActivity.USER_ID);
                        postData.put("comment", comment.getText().toString());
                        postData.put("stuRateEv", rate.getText().toString());

                        PostData task = new PostData(postData);
                        task.execute("https://wimuuv.herokuapp.com/api/student_rate/add");

                        Toast.makeText(getApplicationContext(), "Rate done", Toast.LENGTH_SHORT).show();

                        Log.e("Give Rate Activity", "" + postData.toString());
                        startActivity(i2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    studentRate = null;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}