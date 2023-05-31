package com.example.mealz.ProfileFragment.ProfileView;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mealz.LogInPage.View.LogInActivity;
import com.example.mealz.Network.API_Client;
import com.example.mealz.ProfileFragment.ProfilePresenter.profilePresenter;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.Repository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;


public class ProfileFragment extends Fragment {
    private GoogleSignInClient googleSignInClient;
    profilePresenter presenter;
    Repository repository;
    ImageView imgLogOut;
    ImageView imgFav;
    ImageView imgCal;
    TextView txtFav;
    TextView txtCal;
    TextView userName;
    ImageView imgprofile;
    boolean logged = true;
    FirebaseAuth firebaseAuth;
    public static final String File_Name = "PrefFile";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);
        imgLogOut = view.findViewById(R.id.imglogout);
        imgFav = view.findViewById(R.id.imgfav);
        txtFav = view.findViewById(R.id.txtfav);
        imgCal = view.findViewById(R.id.imgcalendar);
        txtCal = view.findViewById(R.id.txtWeekPlan);
        imgprofile = view.findViewById(R.id.imgProfile);
        userName = view.findViewById(R.id.txtName);
        NavController navController = NavHostFragment.findNavController(this);
        firebaseAuth = FirebaseAuth.getInstance();
        presenter = new profilePresenter(Repository.getInstance(API_Client.getInstance() ,
                ConcreteLocalSource.getInstance(getContext()) , getContext()));

        SharedPreferences pref = this.getContext().getSharedPreferences(LogInActivity.File_Name, Context.MODE_PRIVATE);
        String nameProfile = pref.getString("USERNAME", "unKnown");
        userName.setText(nameProfile);


        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_favouriteFragment);
            }
        });
        txtFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_favouriteFragment);
            }
        });
        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_calenderFragment);
            }
        });
        txtCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_calenderFragment);
            }
        });
        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 123);
            }
        });


        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                imgprofile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void signOut() {

        googleSignInClient.signOut();
        firebaseAuth.signOut();
        logged = false;
        presenter.deletMealsFromRoom();
        presenter.deletPlansFromroom();
        SharedPreferences pref = getActivity().getSharedPreferences(File_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("loggedIn", logged);
        editor.commit();
        Intent intent = new Intent(getActivity(), LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}