package com.example.mealz.LogInPage.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mealz.Home.Home.HomeActivity;
import com.example.mealz.R;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LogInActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private SignInClient signInClient;
    private BeginSignInRequest beginSignInRequest;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 123;
    boolean logged = false;
    TextView username;
    TextView Password;
    TextView signup;
    Button login;
    Button guest;
    String name;
    ImageView googleImg;
    DatabaseReference databaseReference = FirebaseDatabase
            .getInstance()
            .getReferenceFromUrl("https://mealz-ad89b-default-rtdb.firebaseio.com/");
    public static final String File_Name = "PrefFile";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        signInClient = Identity.getSignInClient(this);
        // firebaseAuth.signOut();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        firebaseAuth = FirebaseAuth.getInstance();
        signup = findViewById(R.id.signUpTxt);
        login = findViewById(R.id.loginBtn);
        googleImg = findViewById(R.id.google);
        username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        guest = findViewById(R.id.guestBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name  = username.getText().toString();
                String password = Password.getText().toString();
                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LogInActivity.this, "Fill The Required Data", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(name)) {
                                final String fireBasePassword = snapshot.child(name).child("UserPassword").getValue(String.class);

                                if (fireBasePassword.equals(password)) {
                                    Toast.makeText(LogInActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                    logged=true;

                                    SharedPreferences pref = getSharedPreferences(File_Name, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("USERNAME", name);
                                    editor.putBoolean("loggedIn" , logged);
                                    editor.commit();

                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent.putExtra("username" , username.getText());
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LogInActivity.this, "Wrong Data", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LogInActivity.this, "Wrong Data", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


        googleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


        signup.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), com.example.mealz.SignUpPage.View.SignUpActivity.class);
            startActivity(intent);
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences(File_Name, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("USERNAME", "Guest");
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Handle sign-in failure
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("TAG", "onComplete: success");
                            SharedPreferences pref = getSharedPreferences(File_Name, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("USERNAME", name);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Sign-in failed
                        }
                    }
                });
    }
}