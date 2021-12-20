package com.example.wimuuvapplication.UI.Org;

import android.annotation.SuppressLint;
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
import com.example.wimuuvapplication.UI.Student.EditPerfil;
import com.example.wimuuvapplication.UI.Student.Settings;
import com.example.wimuuvapplication.downloaders.JSONObjDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ProfileOrgFragment extends Fragment {


    TextView name, email, initials;
    JSONObject org;
    String orgName;
    String orgEmail;
    String orgInitials;
    Button editperfil;
    Button settings;

    public ProfileOrgFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONObjDownloader task = new JSONObjDownloader();
        JSONObjDownloader task2 = new JSONObjDownloader();


        try {
            org = task.execute("https://wimuuv.herokuapp.com/api/orgs/"+ OrgLoginActivity.ORG_ID).get();
            orgName = org.getString("name");
            orgEmail = org.getString("email");
            orgInitials = org.getString("initials");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_profile_org, container, false);
        TextView name = (TextView)RootView.findViewById(R.id.nameOrg);
        TextView email = (TextView)RootView.findViewById(R.id.emailOrg);
        TextView initials = (TextView)RootView.findViewById(R.id.initialsOrg);
        Button editperfil = (Button)RootView.findViewById(R.id.editperfilOrg);
        ImageButton settings = (ImageButton)RootView.findViewById(R.id.settingsBtnOrg);
        editperfil.setOnClickListener(this::editOnClick);
        settings.setOnClickListener(this::settingsOnClick);

        name.setText("Nome: " + orgName);
        email.setText("Email: " + orgEmail);
        initials.setText("Iniciais da Org: " + orgInitials);

        return RootView;

    }

    public void editOnClick(View view) {
        Intent intent = new Intent(getContext(), EditPerfil.class);

        startActivity(intent);
    }

    public void settingsOnClick(View view) {
        Intent intent = new Intent(getContext(), SettingsOrgActivity.class);

        startActivity(intent);
    }


}