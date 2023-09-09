package com.clg.gitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class JsonLoginActivity extends AppCompatActivity {
        TextView textView;
        EditText email,password;
        Button signup,login;
        SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_login);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp = getSharedPreferences(Constantsp.PREF,MODE_PRIVATE);

        email = findViewById(R.id.json_login_edittext_emai);
        password = findViewById(R.id.json_login_edittext_password);
        signup = findViewById(R.id.json_login_button_signup);
        login = findViewById(R.id.json_login_button_login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              new ConstantMethod(JsonLoginActivity.this,JsonConnectActivity.class);
            }
        });

     login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (email.getText().toString().trim().equalsIgnoreCase("")){
                 email.setError("Email Id OR Contact Requred");
             } else if (password.getText().toString().trim().equalsIgnoreCase("")) {
                 password.setError("Password is Requried");
             }else {
                 if (new ConnectionDetector(JsonLoginActivity.this).networkConnected()){
                     new Dologin().execute();
                 } else{
                     new ConnectionDetector(JsonLoginActivity.this).networkDisconnected();
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

    private class Dologin  extends AsyncTask <String,String,String>{

        ProgressDialog pd;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(JsonLoginActivity.this);
            pd.setMessage("Process is In Progress");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("email",email.getText().toString());
            hashMap.put("password",password.getText().toString());


            return new MakeServiceCall().MakeServiceCall(Constantsp.Base_url + "login.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getBoolean("Status")== true){
                    new ConstantMethod(JsonLoginActivity.this,object.getString("Message"));
                    JSONArray array = object.getJSONArray("UserData");
                    for (int i = 0; i< array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                        sp.edit().putString(Constantsp.EMAIL, jsonObject.getString("email")).commit();
                        sp.edit().putString(Constantsp.ID, jsonObject.getString("id")).commit();
                        sp.edit().putString(Constantsp.NAME, jsonObject.getString("name")).commit();
                        sp.edit().putString(Constantsp.CONTACT, jsonObject.getString("contact")).commit();
                        sp.edit().putString(Constantsp.PASSWORD, jsonObject.getString("password")).commit();
                        sp.edit().putString(Constantsp.DOB, jsonObject.getString("dob")).commit();
                        sp.edit().putString(Constantsp.CITY, jsonObject.getString("city")).commit();
                        sp.edit().putString(Constantsp.GENDER, jsonObject.getString("gender")).commit();
                    }
                    new ConstantMethod(JsonLoginActivity.this,JsonProfileActivity.class);
                    finish();

                }else {
                    new ConstantMethod(JsonLoginActivity.this,object.getString("Message"));

                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}