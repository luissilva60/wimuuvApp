package com.example.wimuuvapplication.UI;

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

    TextView Name,email,bdate,course,gender;
    TextView nameD,emailD,bdateD,courseD,genderD;
    Button editperfil;
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


    public ProfileFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
            courseString = task2.execute("https://wimuuv.herokuapp.com/api/student_course/1").get();
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
        TextView Name = (TextView)RootView.findViewById(R.id.nome);
        TextView email = (TextView)RootView.findViewById(R.id.email);
        TextView bdate = (TextView)RootView.findViewById(R.id.Birthdate);
        TextView course = (TextView)RootView.findViewById(R.id.Curso);
        TextView gender = (TextView)RootView.findViewById(R.id.Género);
        TextView nameD = (TextView)RootView.findViewById(R.id.textView4);
        TextView emailD = (TextView)RootView.findViewById(R.id.textView5);
        TextView bdateD = (TextView)RootView.findViewById(R.id.textView6);
        TextView courseD = (TextView)RootView.findViewById(R.id.textView8);
        TextView genderD = (TextView)RootView.findViewById(R.id.textView7);
        Button editperfil = (Button)RootView.findViewById(R.id.EditarPerfilbtn);
        Name.setText(stuName);
        email.setText(stuEmail);
        bdate.setText(stuBdate);
        course.setText(courseName);
        gender.setText(stuGender);

        return RootView;
    }
}