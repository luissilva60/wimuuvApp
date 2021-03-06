package com.example.wimuuvapplication.UI.Student;

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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.Login.TermoseCondicoesActivity;
import com.example.wimuuvapplication.LoginDetails.LoginDataSource;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.Org.OrgLoginActivity;
import com.example.wimuuvapplication.downloaders.GetPersons;
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

public class Register extends AppCompatActivity {

    EditText name,email,password,birthdate;
    CheckBox aceitar;
    String postBDate;
    Button signUp;
    JSONArray courses;
    ArrayList<String> listGender;
    ArrayList<String> coursesNames;
    ArrayList<String> course;
    ArrayList<Integer> courseId;
    ArrayAdapter<String> adapterGender;
    ArrayAdapter<String> adapterCourses;
    JSONArray students = null;
    int cursoId1;
    int cursoId2;
    String dayString;
    String monthString;

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
        aceitar = findViewById(R.id.checkBox3);



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

        Log.e("IDS CURSOS",courseId.toString());
        Log.e("IDS CURSOS 2", String.valueOf(courseId.get(0)));

        listGender = new ArrayList<>();

        listGender.add("M");
        listGender.add("F");
        listGender.add("X");

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        dayString = String.valueOf(day);
                        if ( day < 10){
                            dayString = "0" + day;
                        }
                        monthString = String.valueOf(month);
                        if ( month < 10){
                            monthString = "0" + month;
                        }
                        postBDate = year+"-"+monthString+"-"+dayString;
                        String date = year+"-"+monthString+"-"+dayString;
                        birthdate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cursoId = new String();
                GetPersons getStudents = new GetPersons();
                String valorphotoId = "1";
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                String genderAtt = gender.getSelectedItem().toString();

                try {
                    students = getStudents.execute("https://wimuuv.herokuapp.com/api/student").get();
                    JSONObject aux = new JSONObject(students.get(0).toString());

                    for (int position = 0; position < 3; position++) {
                        if (curso.getSelectedItemId() == position){
                            cursoId = String.valueOf(courseId.get(position));
                        }
                    }
                    if (name.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em preto", Toast.LENGTH_SHORT).show();
                        name.setHintTextColor(Color.BLACK);
                    }
                    if (birthdate.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em preto", Toast.LENGTH_SHORT).show();
                        birthdate.setHintTextColor(Color.BLACK);
                    }
                    if (password.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em preto", Toast.LENGTH_SHORT).show();
                        password.setHintTextColor(Color.BLACK);
                    }
                    if (email.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Favor preencher o campo em preto", Toast.LENGTH_SHORT).show();
                        email.setHintTextColor(Color.BLACK);
                    }
                    if (!aceitar.isChecked()){
                        Toast.makeText(getApplicationContext(), "Favor aceitar os termos e condi????es", Toast.LENGTH_SHORT).show();
                        aceitar.setHintTextColor(Color.RED);
                    }
                    else if (!name.getText().toString().isEmpty() && !birthdate.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && aceitar.isChecked()){
                        Map<String, String> postData = new HashMap<>();
                        postData.put("crseId", cursoId);
                        postData.put("gender", genderAtt);
                        postData.put("bdate", birthdate.getText().toString());
                        postData.put("password", password.getText().toString());
                        postData.put("email", email.getText().toString());
                        postData.put("name", name.getText().toString());
                        //postData.put("photoId", valorphotoId);
                        Log.e("IDS CURSOS",courseId.toString());
                        Log.e("IDS CURSOS 2", String.valueOf(courseId.get(0)));

                        PostData task2 = new PostData(postData);
                        task2.execute("https://wimuuv.herokuapp.com/api/student/new");



                        Toast.makeText(getApplicationContext(), "Welcome! "+ name.getText().toString(), Toast.LENGTH_SHORT).show();


                        Log.e("Id Sign up activity: ", ""+ postData.toString());
                        startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    students = null;
                }
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void onClickVerTermos(View v) {
        Intent intent = new Intent(getApplicationContext(), TermoseCondicoesActivity.class);


        startActivity(intent);
    }

}