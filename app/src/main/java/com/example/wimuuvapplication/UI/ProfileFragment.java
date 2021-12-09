package com.example.wimuuvapplication.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;


public class ProfileFragment extends Fragment {

    TextView name,email,bdate,course,currentAge,gender;
    JSONObject student ;
    JSONObject courseString;
    String stuName;
    String stuEmail;
    String stuBdate;
    String courseName;
    int stuCurrentAge;
    String stuGender;
    int stuCourseId;
    int stuPhotoId;
    Button editperfil;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONObjDownloader task = new JSONObjDownloader();
        JSONObjDownloader task2 = new JSONObjDownloader();

        try {
            student = task.execute("https://wimuuv.herokuapp.com/api/student/2" ).get();
            stuName = student.getString("name");
            stuEmail = student.getString("email");
            stuBdate = student.getString("bdate");
            stuGender =  student.getString("gender");
            stuCourseId = student.getInt("crseId");
            stuPhotoId = student.getInt("photoId");
            stuCurrentAge = student.getInt("currentAge");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            courseString = task2.execute("https://wimuuv.herokuapp.com/api/student_course/"+ stuCourseId).get();
            courseName = courseString.getString("name");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView name = (TextView)RootView.findViewById(R.id.name);
        TextView email = (TextView)RootView.findViewById(R.id.email);
        TextView bdate = (TextView)RootView.findViewById(R.id.bdate);
        TextView course = (TextView)RootView.findViewById(R.id.course);
        TextView currentAge = (TextView)RootView.findViewById(R.id.currentAge);
        TextView gender = (TextView)RootView.findViewById(R.id.gender);
        Button editperfil = (Button)RootView.findViewById(R.id.editperfil);
        editperfil.setOnClickListener(this::editOnClick);
        name.setText("Name: " + stuName);
        email.setText("Email: " + stuEmail);
        bdate.setText("Data Nascimento: " + stuBdate);
        course.setText("Curso: " + courseName);
        currentAge.setText("Idade: " + stuCurrentAge);
        gender.setText("Género: " + stuGender);

        return RootView;

    }

    public void editOnClick(View view) {
        Intent intent = new Intent(getContext(),EditPerfil.class);

        startActivity(intent);
    }


}