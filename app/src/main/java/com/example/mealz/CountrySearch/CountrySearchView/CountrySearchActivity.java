package com.example.mealz.CountrySearch.CountrySearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mealz.CountrySearch.CountrySearchPresenter.CountrySearchPresenter;
import com.example.mealz.HomeFragment.Presenter.HomeFragmentPresenter;
import com.example.mealz.HomeFragment.View.HomeFragmentAdapter;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class CountrySearchActivity extends AppCompatActivity implements CountrySearchActivityInterface, OnClickListener {
    SearchView searchView;
    TextView countryName;
    RecyclerView recyclerCountry;
    CountryMealsAdapter adapter;
    CountrySearchPresenter presenter;
    List<MealDetails> meals = new ArrayList<>();
    List<MealDetails> mealsLetterList = new ArrayList<>();
    List<MealDetails> mealsNamesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_search);
        searchView = findViewById(R.id.searchView);
        countryName = findViewById(R.id.countryName);
        recyclerCountry = findViewById(R.id.countryList);

        Intent intent = getIntent();
        String IntentCountryName = intent.getStringExtra("countryName");

        countryName.setText("Meals of " + IntentCountryName);
        recyclerCountry.setHasFixedSize(true);
        GridLayoutManager manager2 = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerCountry.setLayoutManager(manager2);
        presenter = new CountrySearchPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(this)
                , this)
                , this);
        presenter.getMealsByCountry(IntentCountryName);
        adapter = new CountryMealsAdapter(this, meals, this);
        recyclerCountry.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getMealsWithName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText))
                    presenter.getMealsByCountry(IntentCountryName);
                else
                    presenter.getMealsStartsWith(newText);
                return true;
            }
        });
    }

    @Override
    public void showCountryMeals(List<MealDetails> meals) {
        adapter.setList(meals);
        Log.i("TAG", "showCountryMeals: country meal response ");
    }

    @Override
    public void showMealsStartsWith(List<MealDetails> meals) {
        mealsLetterList = meals;
        adapter.setList(meals);
    }

    @Override
    public void showMealsWithName(List<MealDetails> meals) {
        mealsNamesList = meals;
        adapter.setList(meals);
    }


    @Override
    public void onClick(MealDetails mealDetails) {

    }
}