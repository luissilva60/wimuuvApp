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
import com.example.wimuuvapplication.downloaders.PostData;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText name,email,birthdate,password;
    Spinner gender,curso;
    String postBDate;
    Button signUp;
    ArrayList<String> listGender;
    ArrayAdapter<String> adapterGender;
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
        gender = findViewById(R.id.spinnerGender);
        curso = findViewById(R.id.spinnerCurso);
        signUp = findViewById(R.id.button3);

        listGender = new ArrayList<>();

        listGender.add("Male");
        listGender.add("Female");
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
                        String date = day+"/"+month+"/"+year;
                        birthdate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        postData.put("usName", name.getText().toString());
                        postData.put("usBdate", postBDate);
                        postData.put("usGender", "M");
                        postData.put("usEmail", email.getText().toString());
                        postData.put("usPassword", password.getText().toString());

                        JSONArray arr;
                        PostData task = new PostData(postData);
                        arr = task.execute("https://wimuuv.herokuapp.com/api/student/new").get();

                        Toast.makeText(getApplicationContext(), "Welcome !"+ name.getText().toString(), Toast.LENGTH_SHORT).show();


                        LoginDataSource login = new LoginDataSource();
                        login.login(""+email.getText().toString(), ""+password.getText().toString());
                        Log.e("Id Sign up activity", ""+LoginDataSource.ID);
                        startActivity(new Intent(Register.this, MainActivity.class));
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