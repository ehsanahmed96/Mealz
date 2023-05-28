package com.example.mealz.MealDetails.MealDetailsView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.MealDetails.MealDetailsPresenter.MealDetailsPresenter;
import com.example.mealz.MealDetails.MealDetailsPresenter.MealDetailsPresenterInterface;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Recipe;
import com.example.mealz.model.Repository;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsInterface, OnClick {
    YouTubePlayerView youTubePlayerView;
    ImageView mealPic;
    //WeekPlan weekPlan;
    TextView MealName, MealCountry, steps;
    List<Recipe> myIngredients;
    MealDetailsInterface mealDetailsInterface;
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    MealDetailsPresenter presenter;
    ConcreteLocalSource cls;
    MealDetails mealResponse;
    Button btnAddToFav, btnAddToWeekPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        MealName = findViewById(R.id.mealName);
        MealCountry = findViewById(R.id.mealCountry);
        steps = findViewById(R.id.steps);
        myIngredients = new ArrayList<>();
        youTubePlayerView = findViewById(R.id.videoView);
        mealPic = findViewById(R.id.mealImage);
        btnAddToFav = findViewById(R.id.btnAddToFav);
        btnAddToWeekPlan = findViewById(R.id.btnAddToMyPlan);
        mealResponse = new MealDetails();
        presenter = new MealDetailsPresenter(Repository.getInstance(API_Client.getInstance(),
                ConcreteLocalSource.getInstance(this),
                this), this);
        Intent intent = getIntent();
        String mealId = intent.getStringExtra("mealID");
        String mealName = intent.getStringExtra("mealName");
        presenter.getSpecificMeal(mealId);
        Log.i("TAG", "onCreate: " + mealId);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2,
                GridLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerViewIngredients);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             addMealToFavOnClick(mealResponse);
             Toast.makeText(MealDetailsActivity.this, "add to favourites", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void showSpecificItem(MealDetails meal) {
        mealResponse = meal;
        Log.i("TAG", "showSpecificItem:  meal response is hereeeeeeee");
        MealName.setText(meal.getMealName());
        MealCountry.setText(meal.getStrArea());
        createIngredientList(meal);
        adapter = new IngredientsAdapter(this, myIngredients);
        recyclerView.setAdapter(adapter);
        String thumb = "https://www.themealdb.com/images/ingredients/" + meal.getStrIngredient1() + ".png";
        myIngredients.add(new Recipe(meal.getStrIngredient1(), meal.getStrMeasure1(), thumb));
        adapter = new IngredientsAdapter(this, myIngredients);
        recyclerView.setAdapter(adapter);


        Glide.with(this)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(mealPic);
        steps.setText(meal.getStrInstructions());

        if (!meal.getStrYoutube().isEmpty()) {
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = extractVideoIdFromUrl(meal.getStrYoutube());
                    youTubePlayer.loadVideo(videoId, 0);
                    youTubePlayer.pause();
                }
            });
        }

    }

    public static String extractVideoIdFromUrl(String url) {
        String videoId = null;
        Pattern pattern = Pattern.compile("(?<=v(=|/))([\\w-]+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }

    void createIngredientList(MealDetails mealPojo) {

        if (!mealPojo.getStrIngredient1().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient1() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient1(), mealPojo.getStrMeasure1(), thumb));
        }
        if (!mealPojo.getStrIngredient2().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient2() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient2(), mealPojo.getStrMeasure2(), thumb));
        }
        if (!mealPojo.getStrIngredient3().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient3() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient3(), mealPojo.getStrMeasure3(), thumb));
        }
        if (!mealPojo.getStrIngredient4().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient4() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient4(), mealPojo.getStrMeasure4(), thumb));
        }
        if (!mealPojo.getStrIngredient5().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient5() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient5(), mealPojo.getStrMeasure5(), thumb));
        }
        if (!mealPojo.getStrIngredient6().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient6() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient6(), mealPojo.getStrMeasure6(), thumb));
        }
        if (!mealPojo.getStrIngredient7().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient7() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient7(), mealPojo.getStrMeasure7(), thumb));
        }
        if (!mealPojo.getStrIngredient8().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient8() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient8(), mealPojo.getStrMeasure8(), thumb));
        }
        if (!mealPojo.getStrIngredient9().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient9() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient9(), mealPojo.getStrMeasure9(), thumb));
        }
        if (!mealPojo.getStrIngredient10().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient10() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient10(), mealPojo.getStrMeasure10(), thumb));
        }
        if (!mealPojo.getStrIngredient11().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient11() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient11(), mealPojo.getStrMeasure11(), thumb));
        }
        if (!mealPojo.getStrIngredient12().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient12() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient12(), mealPojo.getStrMeasure12(), thumb));
        }
        if (!mealPojo.getStrIngredient13().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient13() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient13(), mealPojo.getStrMeasure13(), thumb));
        }
        if (!mealPojo.getStrIngredient14().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient14() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient14(), mealPojo.getStrMeasure14(), thumb));
        }
        if (!mealPojo.getStrIngredient15().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient15() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient15(), mealPojo.getStrMeasure15(), thumb));
        }
        if (!mealPojo.getStrIngredient16().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient16() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient16(), mealPojo.getStrMeasure16(), thumb));
        }
        if (!mealPojo.getStrIngredient17().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient17() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient17(), mealPojo.getStrMeasure17(), thumb));
        }
        if (!mealPojo.getStrIngredient18().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient18() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient18(), mealPojo.getStrMeasure18(), thumb));
        }
        if (!mealPojo.getStrIngredient19().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient19() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient19(), mealPojo.getStrMeasure19(), thumb));
        }
        if (!mealPojo.getStrIngredient20().isEmpty()) {
            String thumb = "https://www.themealdb.com/images/ingredients/" + mealPojo.getStrIngredient20() + ".png";
            myIngredients.add(new Recipe(mealPojo.getStrIngredient20(), mealPojo.getStrMeasure20(), thumb));
        }

    }
    @Override
    public void addMealToFavOnClick(MealDetails meal) {
        presenter.addToFav(meal);

    }

}