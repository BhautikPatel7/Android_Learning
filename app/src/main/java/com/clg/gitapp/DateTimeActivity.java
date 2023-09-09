package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeActivity extends AppCompatActivity {

     EditText dateedit,timeedit;
     Calendar calendar;

     int ihour,iminute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        dateedit = findViewById(R.id.editdate);
        timeedit = findViewById(R.id.edittime);

        calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateclick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
              calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);

                SimpleDateFormat simpleformate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                dateedit.setText(simpleformate.format(calendar.getTime()));

            }
        };
        dateedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           DatePickerDialog dialog = new DatePickerDialog(DateTimeActivity.this,dateclick,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
          // dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
          // dialog.getDatePicker().setMinDate(System.currentTimeMillis());
           dialog.show();
            }
        });

TimePickerDialog.OnTimeSetListener timeClick = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        ihour = i;
        iminute = i1;
        String sampm = "";
        if (ihour == 12) {
            sampm = "pm";
        } else if (ihour > 12) {
            sampm = "PM";
            ihour -= 12;

        } else if (ihour == 0) {
            sampm = "am";
            ihour = 12;

        } else {
            sampm = "am";
        }

        String smin ="";
        if (iminute < 10){
          smin = "0" + iminute;
        }else {
            smin = String.valueOf(iminute);
        }
    timeedit.setText(ihour+" :"+ iminute +" "+ sampm);




    }
};

timeedit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        new TimePickerDialog(DateTimeActivity.this,timeClick,ihour,iminute,false).show();


    }
});


    }
}