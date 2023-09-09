package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class CustomListSecondActivity extends AppCompatActivity {
    GridView gridView;
    String[] counrtyarry = {"india", "australia", "japan", "usa"};
    int[] imagearry = {R.drawable.clock2, R.drawable.clock2, R.drawable.clock2, R.drawable.clock2};

    ArrayList<CustomList> arryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_second);

        gridView = findViewById(R.id.custom_Gridview_second);
        arryList = new ArrayList<>();
        for (int i = 0; i < counrtyarry.length; i++) {
            CustomList list = new CustomList();
            list.setName(counrtyarry[i]);
            list.setImage(imagearry[i]);
            arryList.add(list);
        }

        CustomListSecondAdapter adapter = new CustomListSecondAdapter(CustomListSecondActivity.this, arryList);
        gridView.setAdapter(adapter);

        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(CustomListSecondActivity.this, TaskoneActivity.class);
                bundle.putInt("imagearry", arryList.get(i).getImage());
                bundle.putString("countryarry", arryList.get(i).getName());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });*/
    }
}