package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class OrgLoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private JSONArray LoginCredentials = null;
    public static String ORG_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_login);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.edittextEmailorg);
        password = findViewById(R.id.editTextPasswordorg);

        email.setText("example2@iol.xyz");
        password.setText("admin");


        // JSON array downloader (liga a task)
        JSONArrayDownloader task = new JSONArrayDownloader();

        LoginCredentials = new JSONArray();
        try {
            LoginCredentials = task.execute("https://wimuuv.herokuapp.com/api/orgs").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onClickLogin(View v) throws JSONException {

        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Credenciais erradas!!! Verifique se está tudo bem!!!", Toast.LENGTH_SHORT).show();
        } else {



            JSONObject student;
            //vamos verificar se dentro do array existem as strings que o utilizador inseriu
            for (int i = 0; i < LoginCredentials.length(); i++) {
                student = LoginCredentials.getJSONObject(i);


                if (student.getString("email").equals(Email) && student.getString("password").equals(Password)) {

                    ORG_ID = student.getString("id");

                    Intent intent = new Intent(getApplicationContext(), OrgActivity.class);
                    startActivity(intent);
                    Log.e(String.valueOf(this), LoginCredentials.get(i).toString());


                }
            }


        }



    }

    public void onClickfgtpw(View v) {
        Intent intent = new Intent(getApplicationContext(), ForgotPasswordOrg.class);


        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}