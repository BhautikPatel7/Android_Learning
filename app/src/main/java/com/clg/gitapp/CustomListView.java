package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

public class CustomListView extends AppCompatActivity {
GridView listView;
String countryArry[] = {"india","usa","japan","south koriya"};
int imagearry [] = {R.drawable.clock2, R.drawable.clock2, R.drawable.clock2, R.drawable.clock2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        listView = findViewById(R.id.custom_listview);

        CustomListAdapter adapter = new CustomListAdapter(CustomListView.this,imagearry,countryArry);
        listView.setAdapter(adapter);
    }
}