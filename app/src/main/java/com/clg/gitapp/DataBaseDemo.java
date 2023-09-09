package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataBaseDemo extends AppCompatActivity {

    EditText email,name,contactno;
    Button insert,update,search,delete;
    String emailPattern =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_demo);

        db = openOrCreateDatabase("student_db",MODE_PRIVATE,null);
       String TableQueary = "CREATE TABLE IF NOT EXISTS RECORD1 (NAME VARCHAR(100),EMAIL VARCHAR(100),CONTACT INT(10))";
       db.execSQL(TableQueary);


        email = findViewById(R.id.Databasedemo_edittext_email);
        name = findViewById(R.id.Databasedemo_edittext_name);
       contactno = findViewById(R.id.Databasedemo_edittext_contactno);

       insert = findViewById(R.id.Databasedemo_button_inster);
       insert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
           if (name.getText().toString().trim().equalsIgnoreCase("")){
               name.setError("email id required");
           } else if (email.getText().toString().trim().equalsIgnoreCase("")) {
               email.setError("email id requred");
           } else if (!email.getText().toString().matches(emailPattern)) {
             email.setError("enter valid email id");
           } else if (contactno.getText().toString().trim().equalsIgnoreCase("")) {
               contactno.setError("contact no. reured ");
           } else if (contactno.getText().toString().length() > 10 || contactno.getText().toString().length() < 10) {
               contactno.setError("enter valid number");
           } else{
               String searchqueary = " SELECT * FROM RECORD1 WHERE CONTACT =  '" + contactno.getText().toString()+"'";
               Cursor cursor = db.rawQuery(searchqueary,null);
               if (cursor.getCount() > 0){
                   new ConstantMethod(DataBaseDemo.this,"this number is already registred");
               }else {

              String insertqueary = " INSERT INTO RECORD1 VALUES('"+email.getText().toString()+"','"+name.getText().toString()+"','" + contactno.getText().toString()+"')";
               db.execSQL(insertqueary);
               new ConstantMethod(DataBaseDemo.this,"insert suscesfully");
               clearData();
               }
           }
           }
       });
       delete = findViewById(R.id.Databasedemo_button_delete);
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (contactno.getText().toString().trim().equalsIgnoreCase("")){
                   contactno.setError("contact no required");
               } else if (contactno.getText().toString().length() > 10 || contactno.getText().toString().length() < 10) {
                   contactno.setError("enter valid contact number");
               }

               else {
                   String searchqueary = " SELECT * FROM RECORD1 WHERE CONTACT =  '" + contactno.getText().toString()+"'";
                   Cursor cursor = db.rawQuery(searchqueary,null);
                   if (cursor.getCount() > 0){
                   String deletequary ="DELETE FROM RECORD1  WHERE CONTACT '" + contactno.getText().toString()+"'";
                   db.execSQL(deletequary);
                  
                   new ConstantMethod(DataBaseDemo.this,"deleted sucessfully");
                   clearData();
                   }

               }

           }
       });

   update = findViewById(R.id.Databasedemo_button_update);
   update.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           if (name.getText().toString().trim().equalsIgnoreCase("")){
               name.setError("email id required");
           } else if (email.getText().toString().trim().equalsIgnoreCase("")) {
               email.setError("email id requred");
           } else if (!email.getText().toString().matches(emailPattern)) {
               email.setError("enter valid email id");
           } else if (contactno.getText().toString().trim().equalsIgnoreCase("")) {
               contactno.setError("contact no. reured ");
           } else if (contactno.getText().toString().length() > 10 || contactno.getText().toString().length() < 10) {
               contactno.setError("enter valid number");
           } else{
               String searchqueary = " SELECT * FROM RECORD1 WHERE CONTACT =  '" + contactno.getText().toString()+"'";
               Cursor cursor = db.rawQuery(searchqueary,null);
               if (cursor.getCount() > 0){
          String updatequeary =  " UPDATE RECORD1 SET NAME = '" + name.getText().toString()+"',EMAIL = '" + email.getText().toString()+"', CONTACT = '" + contactno.getText().toString()+"'";
          db.execSQL(updatequeary);
          new ConstantMethod(DataBaseDemo.this,"updated sucessfully");
          clearData();
               }
           }
       }
   });

   search = findViewById(R.id.Databasedemo_insert_search);
   search.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           if (contactno.getText().toString().trim().equalsIgnoreCase("")){
               contactno.setError("contact no required");
           } else if (contactno.getText().toString().length() > 10 || contactno.getText().toString().length() < 10) {
               contactno.setError("enter valid contact number");
           }
           else{
           String searchqueary = " SELECT * FROM RECORD1 WHERE CONTACT =  '" + contactno.getText().toString()+"'";
                     Cursor cursor = db.rawQuery(searchqueary,null);
                     if (cursor.getCount() > 0){
                         if (cursor.moveToFirst()){
                             name.setText(cursor.getString(0));
                             email.setText(cursor.getString(1));
                             contactno.setText(cursor.getString(2));
                         }
                     }else {
                         new ConstantMethod(DataBaseDemo.this,"invalid contact");
                     }
           }
       }
   });
        }
    private void clearData() {
        name.setText("");
        email.setText("");
        contactno.setText("");
        name.requestFocus();

    }
}