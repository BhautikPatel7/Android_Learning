package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
   ImageView imageView;
   SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.splash_iv);
        getSupportActionBar().hide();
        sp = getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(3000);
        //animation.setRepeatCount(2);
        imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getString(Constantsp.ID,"").equalsIgnoreCase("")){
                    new ConstantMethod(SplashActivity.this,JsonLoginActivity.class);
                    finish();
                }else {
                    new ConstantMethod(SplashActivity.this,JsonProfileActivity.class);
                finish();
                }

          //new ConstantMethod(SplashActivity.this,ActivityToFragment.class);
         // finish();
            }
        },3500);
    }
}