package com.example.mealz.MealDetails.MealDetailsView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.MealResponse;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsInterface{
    YouTubePlayerView youTubePlayerView;
    ImageView mealPic;
    String shP;
    //WeekPlan weekPlan;
    String date2;
    TextView MealName, MealOrigin, steps;
    //ArrayList<Recipe> myIngredients;
    MealDetailsInterface mealDetailsInterface;
    RecyclerView recyclerView;
    //IngredientsAdapter adapter;
    ConcreteLocalSource cls;
    MealResponse mealResponse;
    Button btnAddToFav, btnAddToWeekPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        MealName = findViewById(R.id.mealName);
        Intent intent = getIntent();
        //String tableName = intent.getStringExtra("tableType");
        String mealName = intent.getStringExtra("mealName");
        MealName.setText(mealName);

    }
}