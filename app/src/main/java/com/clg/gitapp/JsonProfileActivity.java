package com.clg.gitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class JsonProfileActivity extends AppCompatActivity {
    SharedPreferences sp;

    EditText name,email,contact,password,confirmpassword,dob;
    RadioButton male,female,transgender;
    RadioGroup gender;
    Spinner city;
    String[] cityArray = {"Ahmedabad","Vadodara","Surat","Rajkot"};
    Button editProfile,submit,deleteProfile,logout;

    String sGender="",sCity="",sGenderInt="0";

    Calendar calendar;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_profile);
        sp = getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);
        getSupportActionBar().setTitle("Welcome"+ sp.getString(Constantsp.NAME,""));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.json_profile_edittext_name);
        email = findViewById(R.id.json_profile_edittext_email);
        contact = findViewById(R.id.json_profile_edittext_contact);
        password = findViewById(R.id.json_profile_edittext_passcode);
        confirmpassword = findViewById(R.id.json_profile_edittext_confirm_passcode);
        dob = findViewById(R.id.json_profile_edittext_date_of_birth);
        gender = findViewById(R.id.json_signup_radiogrop_gender);
        city = findViewById(R.id.json_signup_spinner_city);
        submit = findViewById(R.id.json_profile_button_submit);
        deleteProfile = findViewById(R.id.json_profile_button_delete_profile);
        logout = findViewById(R.id.json_profile_button_logut);
        editProfile = findViewById(R.id.json_Profile_button_editprofile);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                new ConstantMethod(JsonProfileActivity.this,JsonLoginActivity.class);
                finish();
            }
        });

        male = findViewById(R.id.json_profile_radiobutton_male);
       female  = findViewById(R.id.json_profile_radiobutton_female);
        transgender = findViewById(R.id.json_profile_radiobutton_transgender);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                sGender = rb.getText().toString();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(JsonProfileActivity.this, android.R.layout.simple_list_item_1,cityArray);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        city.setAdapter(adapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sCity = cityArray[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateClick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                dob.setText(dateFormat.format(calendar.getTime()));

            }
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(JsonProfileActivity.this,dateClick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
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
                    password.setError("Password Required");
                }
                else if(confirmpassword.getText().toString().trim().equalsIgnoreCase("")){
                    confirmpassword.setError("Confirm Required");
                }
                else if(!password.getText().toString().matches(confirmpassword.getText().toString())){
                    confirmpassword.setError("Password Does Not Match");
                }
                else if(dob.getText().toString().trim().equalsIgnoreCase("")){
                    new ConstantMethod(JsonProfileActivity.this,"Please Select Date Of Birth");
                }
                else if(gender.getCheckedRadioButtonId()==-1){
                    new ConstantMethod(JsonProfileActivity.this,"Please Select Gender");
                }
                else{
                    if(new ConnectionDetector(JsonProfileActivity.this).networkConnected()){
                        //new ConstantMethod(JsonProfileActivity.this,"Internet/Wifi Connected");
                        if(sGender.equalsIgnoreCase("Male")){
                            sGenderInt = "0";
                        }
                        else if(sGender.equalsIgnoreCase("Female")){
                            sGenderInt = "1";
                        }
                        else if(sGender.equalsIgnoreCase("Transgender")){
                            sGenderInt = "2";
                        }
                        else{
                            sGenderInt = "0";
                        }
                        setdata(false);
                        //new JsonSignupActivity.DoSignup().execute();
                    }
                    else{
                        new ConnectionDetector(JsonProfileActivity.this).networkDisconnected();
                    }
                }
            }
        });

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new ConnectionDetector(JsonProfileActivity.this).networkConnected()){
                    new DoDelete().execute();
                }
                else{
                    new ConnectionDetector(JsonProfileActivity.this).networkDisconnected();
                }

            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {setdata(true);}
        });

        setdata(false);
    }



    private void setdata(boolean isEnabled) {

        name.setEnabled(isEnabled);
        email.setEnabled(isEnabled);
        contact.setEnabled(isEnabled);
        password.setEnabled(isEnabled);
        dob.setEnabled(isEnabled);
        male.setEnabled(isEnabled);
        female.setEnabled(isEnabled);
        transgender.setEnabled(isEnabled);
        city.setEnabled(isEnabled);

        if (isEnabled == false){
          confirmpassword.setVisibility(View.GONE);
          editProfile.setVisibility(View.VISIBLE);
          password.setVisibility(View.GONE);
          submit.setVisibility(View.GONE);

        }else {
            confirmpassword.setVisibility(View.VISIBLE);
            editProfile.setVisibility(View.GONE);
            password.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
         }

        name.setText(sp.getString(Constantsp.NAME,""));
        email.setText(sp.getString(Constantsp.EMAIL,""));
        contact.setText(sp.getString(Constantsp.CONTACT,""));
        dob.setText(sp.getString(Constantsp.DOB,""));
        password.setText(sp.getString(Constantsp.PASSWORD,""));

        if (sp.getString(Constantsp.GENDER,"").equals("0")){
            sGender = "Male";
             male.setChecked(true);
        } else if (sp.getString(Constantsp.GENDER,"").equals("1")) {
            sGender = "Female";
            female.setChecked(true);
        } else if (sp.getString(Constantsp.GENDER,"").equals("2")) {
            sGender = "Transgender";
            transgender.setChecked(true);
        }else{

        }

        int icityposition = 0;
        for (int i=0; i<cityArray.length;i++){
            if (sp.getString(Constantsp.CITY,"").equalsIgnoreCase(cityArray[i])){
                icityposition = i;
                break;
            }
        }
        city.setSelection(icityposition);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private class DoDelete extends AsyncTask<String,String,String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(JsonProfileActivity.this);
            pd.setMessage("Process is IN Progress...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("id",sp.getString(Constantsp.ID,""));

            return new MakeServiceCall().MakeServiceCall(Constantsp.Base_url + "deleteprofile.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getBoolean("Status")== true){
                    new ConstantMethod(JsonProfileActivity.this,object.getString("Message"));
                    sp.edit().clear().commit();
                    new ConstantMethod(JsonProfileActivity.this,JsonLoginActivity.class);
                    finish();
                }else {
                    new ConstantMethod(JsonProfileActivity.this,object.getString("Message"));

                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}