package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.LoginDetails.LoginDataSource;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.example.wimuuvapplication.downloaders.PostData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class Register extends AppCompatActivity {
    EditText name,email,password,birthdate;
    String postBDate;
    Button signUp;
    JSONArray courses;
    ArrayList<String> listGender;
    ArrayList<String> coursesNames;
    ArrayList<String> course;
    ArrayList<Integer> courseId;
    ArrayAdapter<String> adapterGender;
    ArrayAdapter<String> adapterCourses;
    int cursoId1;
    int cursoId2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        toolbar2.setTitle("Sign Up");
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress2);
        birthdate = findViewById(R.id.editTextDate);
        password = findViewById(R.id.editTextTextPassword2);
        Spinner gender = (Spinner) findViewById(R.id.spinnerGender);
        Spinner curso = (Spinner) findViewById(R.id.spinnerCurso);
        signUp = findViewById(R.id.button3);



        JSONArrayDownloader task = new JSONArrayDownloader();
        try {
            courses = task.execute("https://wimuuv.herokuapp.com/api/student_course/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject obj;
        course = new ArrayList<>();
        coursesNames = new ArrayList<>();
        courseId = new ArrayList<>();
        if(courses != null) {
            for (int i = 0; i < courses.length();i++){
                try{
                    obj = courses.getJSONObject(i);
                    String courseName = obj.getString("name");

                    courseId.add(obj.getInt("id"));
                    coursesNames.add(obj.getString("name"));
                    course.add(String.format("%s",courseName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        adapterCourses = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, course);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        curso.setAdapter(adapterCourses);



        listGender = new ArrayList<>();

        listGender.add("M");
        listGender.add("F");
        listGender.add("Non Binary");

        adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listGender);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapterGender);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        postBDate = year+"-"+month+"-"+day;
                        String date = year+"/"+month+"/"+day;
                        birthdate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursoId1 = curso.getId();
                Long cursoId = curso.getSelectedItemId();
                String genderAtt = gender.getSelectedItem().toString();
                try {
                    if (name.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        name.setHintTextColor(Color.RED);
                    }
                    if (birthdate.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        birthdate.setHintTextColor(Color.RED);
                    }
                    if (password.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        password.setHintTextColor(Color.RED);
                    }
                    if (email.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em vermelho", Toast.LENGTH_SHORT).show();
                        email.setHintTextColor(Color.RED);
                    }
                    else {
                        Map<String, String> postData = new HashMap<>();
                        postData.put("name", name.getText().toString());
                        postData.put("bdate", birthdate.getText().toString());
                        postData.put("gender", genderAtt);
                        postData.put("email", email.getText().toString());
                        postData.put("password", password.getText().toString());
                        postData.put("crseId", courseId.toString());

                        JSONArray arr;
                        PostData task2 = new PostData(postData);
                        arr = task2.execute("https://wimuuv.herokuapp.com/api/student/new").get();



                        Toast.makeText(getApplicationContext(), "Welcome ! "+ name.getText().toString(), Toast.LENGTH_SHORT).show();


                        //LoginDataSource login = new LoginDataSource();
                        //login.login(""+email.getText().toString(), ""+password.getText().toString());
                        Log.e("Id Sign up activity", ""+ postData.toString());
                        //startActivity(new Intent(Register.this, MainActivity.class));
                    }
                } catch (Exception e) {
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