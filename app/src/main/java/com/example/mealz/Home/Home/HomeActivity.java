package com.example.mealz.Home.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mealz.LogInPage.View.LogInActivity;
import com.example.mealz.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

public class HomeActivity extends AppCompatActivity {
    NavigationView navigationView;
    NavController navController;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bottomNavigationView = findViewById(R.id.NavbBar);
        navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        SharedPreferences pref = this.getSharedPreferences(LogInActivity.File_Name, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.favouriteFragment) {
                    if (shP.equals("Guest")) {
                        login();
                        Toast.makeText(HomeActivity.this, "login first", Toast.LENGTH_SHORT).show();
                    } else {
                        navController.navigate(R.id.favouriteFragment);
                    }
                } else if (item.getItemId() == R.id.calenderFragment) {
                    if (shP.equals("Guest")) {
                        login();
                        Toast.makeText(HomeActivity.this, "login first", Toast.LENGTH_SHORT).show();
                    } else {
                        navController.navigate(R.id.calenderFragment);
                    }
                } else if (item.getItemId() == R.id.profileFragment) {
                    if (shP.equals("Guest")) {
                        login();
                        Toast.makeText(HomeActivity.this, "login first", Toast.LENGTH_SHORT).show();
                    } else {
                        navController.navigate(R.id.profileFragment);

                    }
                } else if (item.getItemId() == R.id.homeFragment) {
                    navController.navigate(R.id.homeFragment);
                } else if (item.getItemId() == R.id.searchFragment) {
                    navController.navigate(R.id.searchFragment);
                }
                return true;

            }
        });
    }

    void login() {
        AlertDialog dialog = new MaterialAlertDialogBuilder(this).setTitle("Error  ")
                .setMessage("You need to login first ").setPositiveButton("Login ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }


}

