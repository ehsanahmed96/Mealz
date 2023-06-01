package com.example.mealz.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.mealz.Home.Home.HomeActivity;
import com.example.mealz.LogInPage.View.LogInActivity;
import com.example.mealz.R;

public class MainActivity extends AppCompatActivity {
    public static final String File_Name = "PrefFile";
    SharedPreferences pref;
    boolean logged = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(File_Name, Context.MODE_PRIVATE);
        logged = (pref.getBoolean("loggedIn", false));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logged) {
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, 3000);


    }
}