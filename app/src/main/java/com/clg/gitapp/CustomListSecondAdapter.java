package com.clg.gitapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;

public class CustomListSecondAdapter extends BaseAdapter {
    Context context;
    ArrayList<CustomList> arrayList;

    public CustomListSecondAdapter(Context context, ArrayList<CustomList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_second_custom_list_view, null);

        ImageView image = view.findViewById(R.id.second_custom_list_imageview);
        TextView name = view.findViewById(R.id.second_custom_list_textview);
        name.setText(arrayList.get(i).getName());
        image.setImageResource(arrayList.get(i).getImage());

      view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              new ConstantMethod(context,arrayList.get(i).getName());
              Bundle bundle = new Bundle();
              Intent intent = new Intent(context, TaskoneActivity.class);
              bundle.putInt("imagearry", arrayList.get(i).getImage());
              bundle.putString("countryarry", arrayList.get(i).getName());
              intent.putExtras(bundle);

              context.startActivity(intent);
          }
      });
        return view;
    }
}
