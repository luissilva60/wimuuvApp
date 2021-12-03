package com.example.wimuuvapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wimuuvapplication.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView leftIcon = findViewById(R.id.left_icon);
        ImageView rightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this,"You clicked in left icon",Toast.LENGTH_SHORT).show();
            }
        });
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this,"You clicked in right icon",Toast.LENGTH_SHORT).show();
            }
        });

        title.setText("Profile");
    }

    public void onClickMap(View v) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);


        startActivity(intent);
    }
    public void onClickFeed(View v) {
        Intent intent = new Intent(getApplicationContext(), Feed.class);


        startActivity(intent);
    }
    public void onClickProfile(View v) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);


        startActivity(intent);
    }
}