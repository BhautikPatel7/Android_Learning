package com.clg.gitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter;

public class Add_Catagory_Activity extends AppCompatActivity {
    EditText edittext;
    Button select, upload;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catagory);
        edittext = findViewById(R.id.add_catagory_edittext);
        select = findViewById(R.id.button_Select_image);
        upload = findViewById(R.id.button_upload_image);
        imageView = findViewById(R.id.add_catagory_imageview);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edittext.getText().toString().trim().equalsIgnoreCase("")) {
                    edittext.setError("Catgory Requried");
                } else {

                }
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FishBun.with(Add_Catagory_Activity.this)
                        .setImageAdapter(new GlideAdapter())
                        .setPickerCount(1)
                        .setReachLimitAutomaticClose(false)
                        .startAlbumWithOnActivityResult(123);
            }
        });

    }
}