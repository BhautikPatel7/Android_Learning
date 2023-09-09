package com.clg.gitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Activity_tab_demo extends AppCompatActivity {
TabLayout tabLayout;
ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_demo);
        tabLayout = findViewById(R.id.tab_demo_layout);
        pager = findViewById(R.id.pageview_tab_demo);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {tabLayout.setupWithViewPager(pager);}


        });


    }
private class Tabdemoadapter extends FragmentPagerAdapter{
 public Tabdemoadapter(FragmentManager supportFragmentmanager){
     super(supportFragmentmanager);
 }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }


    @Override
    public int getCount() {
        return 0;
    }
}
}

