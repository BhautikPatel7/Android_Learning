package com.clg.gitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

    Context context;
    int imagearry[];
    String namearry[];
    public CustomListAdapter(Context context, int[] imagearry, String[] countryArry) {
      this.context = context;
      this.imagearry = imagearry;
      namearry = countryArry;


    }

    @Override
    public int getCount() {
        return namearry.length;
    }

    @Override
    public Object getItem(int i) {
        return namearry[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.activity_custom_list,null);

        TextView name = view.findViewById(R.id.custom_list_textview);
        ImageView image = view.findViewById(R.id.custom_list_imageview);

        name.setText(namearry[i]);
        image.setImageResource(imagearry[i]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new ConstantMethod(context,namearry[i]);
            }
        });

        return view;
    }
}
