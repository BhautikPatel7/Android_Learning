package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
   Button done;
   EditText email,password;

    String   emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        done = findViewById(R.id.button_done);
        email = findViewById(R.id.email_main);
        password = findViewById(R.id.passcode);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("")){
                     email.setError("enter email is needful");
                }
                else if (!email.getText().toString().matches(emailPattern)){
                    email.setError("enter valid email id");
                }
                else if (password.getText().toString().trim().equals("")){
                    password.setError("Try To Remamber passcode");
                }
                else {
                    if (email.getText().toString().trim().equals("bhautik99@gmail.com") && (password.getText().toString().equalsIgnoreCase("bhautik99"))) {


                        System.out.println("We Recoreded");

                        // Toast.makeText(MainActivity.this, "we recorded",Toast.LENGTH_LONG).show();
                        //  Snackbar.make(view,"We Recorded Sucessfully", Snackbar.LENGTH_LONG).show();
                        new ConstantMethod(MainActivity.this, "we recorded");
                      //  new ConstantMethod(view, "WE Recorded Sucessfully");
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        Bundle  bundle = new Bundle();
                        bundle.putString("EMAIL",email.getText().toString());
                        bundle.putString("PASSCODE",password.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                 else{
                     new ConstantMethod(MainActivity.this,"Plese Retry");
                        //new constantmethod(view, "I dont Think That you alredy registred");

                    }
                }
            }
        });
    }
}