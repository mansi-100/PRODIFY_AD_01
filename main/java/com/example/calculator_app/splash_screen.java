package com.example.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class splash_screen extends AppCompatActivity {
private static final int Splash_timeout=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              Intent intent=new Intent(splash_screen.this, MainActivity.class);
              startActivity(intent);
              finish();
          }
      },2000
      );


    }
}