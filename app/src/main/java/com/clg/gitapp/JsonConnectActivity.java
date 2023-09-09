package com.clg.gitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.AsynchronousChannelGroup;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class JsonConnectActivity extends AppCompatActivity {

    TextView textView;
    EditText name,email,contact,password,confirmpassword,dob;
    RadioGroup gender;
    Button signup;
    Calendar calender;
    Spinner city;
    String CityArry[] = {"Ahmedabad","Rajkot","Vadodara","Mumbai","Delhi","Surat","Noida","Amritsar","Anand","Benaluru","indore","pune","kolkata"};
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String sgender="",scity="",sgenderInt="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_connect);
        getSupportActionBar().setTitle("Sign Up form");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.json_signup_edittext_name);
        email = findViewById(R.id.json_signup_edittext_email);
        contact = findViewById(R.id.json_signup_edittext_contact);
        password = findViewById(R.id.json_signup_edittext_passcode);
        confirmpassword = findViewById(R.id.json_signup_edittext_confirm_passcode);
        dob = findViewById(R.id.json_signup_edittext_date_of_birth);
        gender = findViewById(R.id.json_signup_radiogrop_gender);
        city = findViewById(R.id.json_signup_spinner_city);
        signup = findViewById(R.id.json_signup_button_signup);


        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                rb.getText().toString();

            }
        });

        calender = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateclick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calender.set(Calendar.YEAR,i);
                calender.set(Calendar.MONTH,i1);
                calender.set(Calendar.DAY_OF_MONTH,i2);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                dob.setText(dateFormat.format(calender.getTime()));
            }
        };

      dob.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          DatePickerDialog dialog = new DatePickerDialog(JsonConnectActivity.this,dateclick,calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
          dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
          dialog.show();
          }
      });

        ArrayAdapter adapter = new ArrayAdapter(JsonConnectActivity.this, android.R.layout.simple_expandable_list_item_1,CityArry);
       adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
       city.setAdapter(adapter);

       city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               scity = CityArry[i];
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(name.getText().toString().trim().equalsIgnoreCase("")){
                   name.setError("Name Required");
               }
               else if(email.getText().toString().trim().equalsIgnoreCase("")){
                   email.setError("Email Id Required");
               }
               else if(!email.getText().toString().matches(emailPattern)){
                   email.setError("Valid Email Id Required");
               }
               else if(contact.getText().toString().trim().equalsIgnoreCase("")){
                   contact.setError("Contact No. Required");
               }
               else if(contact.getText().toString().trim().length()<10 || contact.getText().toString().length()>10){
                   contact.setError("Valid Contact No. Required");
               }
               else if(password.getText().toString().trim().equalsIgnoreCase("")){
                   password.setError("Passcode Required");
               }
               else if(confirmpassword.getText().toString().trim().equalsIgnoreCase("")){
                   confirmpassword.setError("Confirm Passcode Required");
               }
               else if(!password.getText().toString().matches(confirmpassword.getText().toString())){
                   confirmpassword.setError("Passcode Does Not Match");
               }
               else if(dob.getText().toString().trim().equalsIgnoreCase("")){
                   new ConstantMethod(JsonConnectActivity.this,"Please Select Date Of Birth");
               }
               else if(gender.getCheckedRadioButtonId()==-1){
                   new ConstantMethod(JsonConnectActivity.this,"Please Select Gender");
               }else {
                 if (new ConnectionDetector(JsonConnectActivity.this).networkConnected()){
                     if (sgender.equalsIgnoreCase("male")){
                        sgenderInt = "0";
                     } else if (sgender.equalsIgnoreCase("Female")) {
                         sgenderInt = "1";
                     } else if (sgender.equalsIgnoreCase("Transgender")) {
                         sgenderInt= "2";
                     }else {
                         sgenderInt ="0";
                     }
                     new DosignUp().execute();
                 }
                 else {
                     new ConnectionDetector(JsonConnectActivity.this).networkDisconnected();
                 }
               }
           }
       });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class DosignUp  extends AsyncTask<String,String,String> {
   ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(JsonConnectActivity.this);
            pd.setMessage("Process is IN Progress");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("email",email.getText().toString());
            hashMap.put("name",name.getText().toString());
            hashMap.put("contact",contact.getText().toString());
            hashMap.put("password",password.getText().toString());
            hashMap.put("dob",dob.getText().toString());
            hashMap.put("gender",sgender);
            hashMap.put("city",scity);

            return new MakeServiceCall().MakeServiceCall(Constantsp.Base_url + "signup.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getBoolean("Status")== true){
                    new ConstantMethod(JsonConnectActivity.this,object.getString("Message"));
                }else {
                    new ConstantMethod(JsonConnectActivity.this,object.getString("Message"));

                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}