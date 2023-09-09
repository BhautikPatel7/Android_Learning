package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskoneActivity extends AppCompatActivity {
ImageView image;
TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskone);
        image = findViewById(R.id.taskone_image);
        name = findViewById(R.id.taskone_text);

       Bundle bundle = getIntent().getExtras();
       image.setImageResource(bundle.getInt("imagearry"));
       name.setText(bundle.getString("countryarry"));

    }
}