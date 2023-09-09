package com.clg.gitapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DemoFragment extends Fragment {

public DemoFragment(){
    //required public empty consturtor becouse of avoid run time exceptions
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_demo, container, false);
        return view;
    }
}