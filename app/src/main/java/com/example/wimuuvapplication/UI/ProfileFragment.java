package com.example.wimuuvapplication.UI;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;


public class ProfileFragment extends Fragment {

    TextView Name;

    JSONObject student ;
    String stuName;
    String stuEmail;
    String stuBdate;
    char stuGender;
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
        try {
            student = task.execute("https://wimuuv.herokuapp.com/api/student/2" ).get();
            stuName = student.getString("name");
            stuEmail = student.getString("email");
            stuBdate = student.getString("bdate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");

            stuGender = (char) student.get("gender");
            stuCourseId = student.getInt("crseId");
            stuPhotoId = student.getInt("photoId");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Name = (TextView) getView().findViewById(R.id.Name);
        Name.setText(stuName);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}