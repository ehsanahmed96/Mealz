package com.example.mealz.SignUpPage.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealz.Home.Home.HomeActivity;
import com.example.mealz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    TextView username;
    TextView Password;
    TextView confirmpassword;
    TextView login;
    Button signupBtn;
    public static final String File_Name = "PrefFile";
    boolean logged = false;
    DatabaseReference databaseReference = FirebaseDatabase
            .getInstance()
            .getReferenceFromUrl("https://mealz-ad89b-default-rtdb.firebaseio.com/");

    String regexPassword = "^[A-Za-z]\\w.{5,15}$";
    String regexUserName = "^[A-Za-z]\\w.{5,15}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.ConfirmPassword);
        login = findViewById(R.id.loginTxt);
        signupBtn = findViewById(R.id.signUpBtn);

        login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), com.example.mealz.LogInPage.View.LogInActivity.class);
            startActivity(intent);
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String password = Password.getText().toString();
                String confirm = confirmpassword.getText().toString();

                if (name.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                } else if (!isValidUsername(name) || (!checkPassword(password , confirm))) {
                    Toast.makeText(SignUpActivity.this, "Strong user name&password is required ", Toast.LENGTH_SHORT).show();

                } else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(name)) {
                                Toast.makeText(SignUpActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("Users").child(name).child("UserPassword").setValue(password);
                                logged=true;
                                SharedPreferences pref = getSharedPreferences(File_Name, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("USERNAME", name);
                                editor.putBoolean("loggedIn" , logged);
                                editor.commit();

                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("username" , username.getText());
                                startActivity(intent);
                                Toast.makeText(SignUpActivity.this, "signup Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

    public boolean isValidUsername(String name) {
        if (name.matches(regexUserName)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassword(String password, String confirmpassword) {
        if (password.equals(confirmpassword) && password.matches(regexPassword)) {
            return true;
        } else {
            return false;
        }
    }
}