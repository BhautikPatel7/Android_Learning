package com.clg.gitapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ConstantMethod {

    ConstantMethod(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    ConstantMethod(View view, String message){
        Snackbar.make( view,message,Snackbar.LENGTH_SHORT).show();

    }
    ConstantMethod(Context context,Class<?> nextClass){
            Intent intent = new Intent(context,nextClass);
            context.startActivity(intent);
    }
}
