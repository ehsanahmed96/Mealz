package com.example.mealz.MealDetails.MealDetailsView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealz.CalenderDialog;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.MealDetails.MealDetailsPresenter.MealDetailsPresenter;
import com.example.mealz.MealDetails.MealDetailsPresenter.MealDetailsPresenterInterface;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Recipe;
import com.example.mealz.model.Repository;
import com.example.mealz.model.WeekPlan;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsInterface, OnClick, DatePickerDialog.OnDateSetListener {
    YouTubePlayerView youTubePlayerView;
    ImageView mealPic;
    WeekPlan weekPlan;
    TextView MealName, MealCountry, steps;
    List<Recipe> myIngredients;
    RecyclerView recyclerView;
    IngredientsAdapter adapter;
    MealDetailsPresenter presenter;
    String key;
    MealDetails mealResponse;
    Button btnAddToFav, btnAddToWeekPlan;
    public static final String File_Name = "PrefFile";

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
        String typeTable = intent.getStringExtra("tableType");

        SharedPreferences prefs = getSharedPreferences(File_Name, Context.MODE_PRIVATE);

        key = prefs.getString("USERNAME", "unknwon");


        Log.i("TAG", "onCreate: user name is  " + key);
        Log.i("TAG", "onCreate: typetable" + typeTable);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2,
                GridLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerViewIngredients);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        if (isNetworkAvailable(getApplicationContext())) {
            presenter.getSpecificMeal(mealId);
            Log.i("TAG", "onCreate: " + mealId);

        } else if (typeTable.equals("favourite")) {
            Log.i("TAG", "onCreate: favourite table favourite meaal is presented");
            presenter.getOffMealDetail(mealName).observe(this, new Observer<MealDetails>() {
                @Override
                public void onChanged(MealDetails mealDetail) {
                    mealResponse = mealDetail;
                    MealName.setText(mealDetail.getMealName());
                    MealCountry.setText(mealDetail.getStrArea());
                    createIngredientList(mealDetail);
                    adapter = new IngredientsAdapter(getApplicationContext(), myIngredients);
                    recyclerView.setAdapter(adapter);
                    String thumb = "https://www.themealdb.com/images/ingredients/" + mealDetail.getStrIngredient1() + ".png";
                    myIngredients.add(new Recipe(mealDetail.getStrIngredient1(), mealDetail.getStrMeasure1(), thumb));
                    adapter = new IngredientsAdapter(getApplicationContext(), myIngredients);
                    recyclerView.setAdapter(adapter);
                    Glide.with(getApplicationContext())
                            .load(mealDetail.getStrMealThumb())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_foreground)
                            .into(mealPic);
                    steps.setText(mealDetail.getStrInstructions());


                }
            });

        } else if (typeTable.equals("weekPlan")) {
            presenter.getOffMealPlan(mealName).observe(this, new Observer<WeekPlan>() {
                @Override
                public void onChanged(WeekPlan plan) {
                    MealName.setText(plan.getStrMeal());
                    MealCountry.setText(plan.getStrArea());
                    createIngredientListForWeek(plan);
                    adapter = new IngredientsAdapter(getApplicationContext(), myIngredients);
                    recyclerView.setAdapter(adapter);
                    String thumb = "https://www.themealdb.com/images/ingredients/" + plan.getStrIngredient1() + ".png";
                    myIngredients.add(new Recipe(plan.getStrIngredient1(), plan.getStrMeasure1(), thumb));
                    adapter = new IngredientsAdapter(getApplicationContext(), myIngredients);
                    recyclerView.setAdapter(adapter);
                    Glide.with(getApplicationContext())
                            .load(plan.getStrMealThumb())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_foreground)
                            .into(mealPic);
                    steps.setText(plan.getStrInstructions());


                }
            });

        }


        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMealToFavOnClick(mealResponse);
                addMealInFirebase(mealResponse , key);
                Toast.makeText(MealDetailsActivity.this, "add to favourites", Toast.LENGTH_SHORT).show();
            }
        });
        btnAddToWeekPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CalenderDialog calenderDialog = new CalenderDialog();
                calenderDialog.show(getSupportFragmentManager(), "Calender");
                weekPlan = new WeekPlan(mealResponse.getIdMeal(), mealResponse.getMealName(), mealResponse.getStrCategory(), mealResponse.getStrArea()
                        , mealResponse.getStrInstructions(), mealResponse.getStrMealThumb()
                        , mealResponse.getStrIngredient1(), mealResponse.getStrIngredient2()
                        , mealResponse.getStrIngredient3(), mealResponse.getStrIngredient4()
                        , mealResponse.getStrIngredient5(), mealResponse.getStrIngredient6()
                        , mealResponse.getStrIngredient7(), mealResponse.getStrIngredient8()
                        , mealResponse.getStrIngredient9(), mealResponse.getStrIngredient10()
                        , mealResponse.getStrIngredient11(), mealResponse.getStrIngredient12()
                        , mealResponse.getStrIngredient13(), mealResponse.getStrIngredient14()
                        , mealResponse.getStrIngredient15(), mealResponse.getStrIngredient16()
                        , mealResponse.getStrIngredient17(), mealResponse.getStrIngredient18()
                        , mealResponse.getStrIngredient19(), mealResponse.getStrIngredient20());

            }
        });

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void addMealToFav(MealDetails meal) {
        presenter.addToFav(meal);
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

    @Override
    public void addMealInFirebase(MealDetails mealDetail, String key) {
        presenter.addMealToFavFirebase(mealDetail, key);
    }

    @Override
    public void addMealInWeekPlanFirebase(WeekPlan mealDetail, String key) {
        presenter.addMealToWeekPlanFirebase(mealDetail, key);
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

    void createIngredientListForWeek(WeekPlan mealPojo) {

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
        addMealToFav(meal);


    }

    @Override
    public void addMealsToWeekPlanOnClick(WeekPlan meals) {
        presenter.addToWeekPlan(meals);
        Toast.makeText(this, "meal Added to Plan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        if (date.contains("Saturday")) {
            weekPlan.sat = "1";
            //presenter.updateSat("Saturday" , "1");

        } else {
            weekPlan.sat = "0";
        }
        if (date.contains("Sunday")) {
            weekPlan.sun = "1";
        } else {
            weekPlan.sun = "0";
        }
        if (date.contains("Monday")) {
            weekPlan.mon = "1";

        } else {
            weekPlan.mon = "0";
        }
        if (date.contains("Tuesday")) {
            weekPlan.tues = "1";

        } else {
            weekPlan.tues = "0";
        }
        if (date.contains("Thursday")) {
            weekPlan.thurs = "1";

        } else {
            weekPlan.thurs = "0";
        }
        if (date.contains("Friday")) {
            weekPlan.fri = "1";

        } else {
            weekPlan.fri = "0";
        }
        if (date.contains("Wednesday")) {
            weekPlan.wed = "1";

        } else {
            weekPlan.wed = "0";
        }
        addMealsToWeekPlanOnClick(weekPlan);
        addMealInWeekPlanFirebase(weekPlan ,key);



    }

}