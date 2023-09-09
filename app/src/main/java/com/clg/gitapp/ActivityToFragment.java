package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityToFragment extends AppCompatActivity {
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_fragment);
        button = findViewById(R.id.show_fragment_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // not working intent in  fragment  Intent intent = new Intent(ActivityToFragment.this,DemoFragment.class);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.activity_to_fragment_layout,new DemoFragment()).commit();
            }
        });


    }
}