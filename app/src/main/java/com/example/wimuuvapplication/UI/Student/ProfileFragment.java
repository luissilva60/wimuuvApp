package com.example.wimuuvapplication.UI.Student;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wimuuvapplication.Login.MainActivity;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.databinding.FragmentMapsBinding;
import com.example.wimuuvapplication.databinding.FragmentProfileBinding;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class ProfileFragment extends Fragment {

    private TextView name,email,bdate,course,currentAge,gender;
    private JSONObject student;
    private JSONObject courseString;
    private String stuName;
    private String stuEmail;
    private String stuBdate;
    private String courseName;
    private int stuCurrentAge;
    private String stuGender;
    private int stuCourseId;
    private Button editperfil;
    private Button settings;
    private Button historicoEv;
    private FragmentProfileBinding binding;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONObjDownloader task = new JSONObjDownloader();
        JSONObjDownloader task2 = new JSONObjDownloader();


        try {
            student = task.execute("https://wimuuv.herokuapp.com/api/student/"+ MainActivity.USER_ID).get();
            stuName = student.getString("name");
            stuEmail = student.getString("email");
            stuBdate = student.getString("bdate");
            stuGender =  student.getString("gender");
            stuCourseId = student.getInt("crseId");
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View RootView = binding.getRoot();
        historicoEv = binding.eventshistorico;
        historicoEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HistoricoDeEventosActivity.class);
                startActivity(i);
            }
        });

        TextView name = (TextView)RootView.findViewById(R.id.name);
        TextView email = (TextView)RootView.findViewById(R.id.email);
        TextView bdate = (TextView)RootView.findViewById(R.id.bdate);
        TextView course = (TextView)RootView.findViewById(R.id.course);
        TextView currentAge = (TextView)RootView.findViewById(R.id.currentAge);
        TextView gender = (TextView)RootView.findViewById(R.id.gender);
        Button editperfil = (Button)RootView.findViewById(R.id.editperfil);
        ImageButton settings = (ImageButton)RootView.findViewById(R.id.settingsBtn);
        editperfil.setOnClickListener(this::editOnClick);
        settings.setOnClickListener(this::settingsOnClick);
        name.setText("Nome: " + stuName);
        email.setText("Email: " + stuEmail);
        bdate.setText("Data Nascimento: " + stuBdate);
        course.setText("Curso: " + courseName);
        currentAge.setText("Idade: " + stuCurrentAge);
        gender.setText("Género: " + stuGender);

        return RootView;

    }

    

    public void editOnClick(View view) {
        Intent intent = new Intent(getContext(), EditPerfil.class);

        startActivity(intent);
    }

    public void settingsOnClick(View view) {
        Intent intent = new Intent(getContext(), Settings.class);

        startActivity(intent);
    }

    /*public void historicoOnClick(View view) {
        Intent intent = new Intent(getContext(), HistoricoDeEventosActivity.class);

        startActivity(intent);
    }*/

}