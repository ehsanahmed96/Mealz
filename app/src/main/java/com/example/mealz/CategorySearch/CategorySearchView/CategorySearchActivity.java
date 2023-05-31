package com.example.mealz.CategorySearch.CategorySearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mealz.CategorySearch.CategorySearchPresenter.CategorySearchPresenter;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchActivity extends AppCompatActivity implements CategorySearchActivityInterface , OnClickListener {
    SearchView searchView;
    TextView categoryName;
    RecyclerView recyclerCategory;
    CategoryMealsAdapter adapter;
    CategorySearchPresenter presenter;
    List<MealDetails> meals = new ArrayList<>();
    List<MealDetails> mealsLetterList = new ArrayList<>();
    List<MealDetails> mealsNamesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_search);
        searchView = findViewById(R.id.searchViewing);
        categoryName = findViewById(R.id.categoryName);
        recyclerCategory = findViewById(R.id.categoryList);

        Intent intent = getIntent();
        String IntentCategoryName = intent.getStringExtra("categoryName");

        categoryName.setText("Meals of " + IntentCategoryName);
        recyclerCategory.setHasFixedSize(true);
        GridLayoutManager manager2 = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerCategory.setLayoutManager(manager2);
        presenter = new CategorySearchPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(this)
                , this)
                , this);
        presenter.getMealsByCategory(IntentCategoryName);
        adapter = new CategoryMealsAdapter(this, meals, this);
        recyclerCategory.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getMealsWithName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText))
                    presenter.getMealsByCategory(IntentCategoryName);
                else
                    presenter.getMealsStartsWith(newText);
                return true;
            }
        });
    }

    @Override
    public void showCategoryMeals(List<MealDetails> meals) {
        adapter.setList(meals);
        Log.i("TAG", "showCountryMeals: category meal response ");
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