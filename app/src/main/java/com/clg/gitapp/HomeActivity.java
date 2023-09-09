package com.clg.gitapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity{

    TextView email,passcode;
    RadioButton male,female,transgender;
    RadioGroup gender;
    CheckBox androidChek,flutter,reactiveNative,ios;
    Button next;



    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.home_email);
        passcode = findViewById(R.id.home_passcode);


        male =findViewById(R.id.home_male);
        female =findViewById(R.id.home_female);
        transgender =findViewById(R.id.home_transgender);


        Bundle bundle = getIntent().getExtras();
        email.setText(bundle.getString("EMAIL"));
        passcode.setText(bundle.getString("PASSCODE"));



        gender = findViewById(R.id.radio_group);

       gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                genderMessageShow(rb.getText().toString());
           }


       });

        androidChek = findViewById(R.id.home_android);
        flutter = findViewById(R.id.home_flutter);
        reactiveNative = findViewById(R.id.home_reactnative);
        ios = findViewById(R.id.home_ios);

        next = findViewById(R.id.nextbutton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();

                if(androidChek.isChecked()){
                    sb.append(androidChek.getText().toString()+"\n");
                }
                if(flutter.isChecked()){
                    sb.append(flutter.getText().toString()+"\n");
                }
                if(reactiveNative.isChecked()){
                    sb.append(reactiveNative.getText().toString()+"\n");
                } if(ios.isChecked()){
                    sb.append(ios.getText().toString()+"\n");

                }
                if (sb.toString().equalsIgnoreCase("")){

                }

                else{
                  genderMessageShow(sb.toString());
                }

                Intent intent = new Intent(HomeActivity.this,SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MALE",male.getText().toString());
                bundle.putString("TRANS",transgender.getText().toString());
                bundle.putString("FEMALE",female.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);


            }


        });



        //flutter.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {

              //  new ConstantMethod(HomeActivity.this,flutter.getText().toString());
            //}
       // });




       // male = findViewById(R.id.home_male);
       // female = findViewById(R.id.home_female);
        // transgender = findViewById(R.id.home_transgender);


       /* male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ConstantMethod(HomeActivity.this,male.getText().toString());
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ConstantMethod(HomeActivity.this,female.getText().toString());
            }
        });
       transgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ConstantMethod(HomeActivity.this,transgender.getText().toString());
            }
        });*/


    }

    private void genderMessageShow(String message) {

            new ConstantMethod(HomeActivity.this,message);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}