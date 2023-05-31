package com.example.mealz.IngredientsSearch.IngredientsSearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;
import android.widget.TextView;
import com.example.mealz.CategorySearch.CategorySearchView.CategoryMealsAdapter;
import com.example.mealz.HomeFragment.View.OnClickListener;
import com.example.mealz.IngredientsSearch.IngredientaSearchPresenter.IngredientSearchpresenter;
import com.example.mealz.Network.API_Client;
import com.example.mealz.R;
import com.example.mealz.dp.ConcreteLocalSource;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class IngredientSearchActivity extends AppCompatActivity implements  IngredientSearchInterface , OnClickListener {
    SearchView searchView;
    TextView ingredientName;
    RecyclerView recyclerIngredient;
    CategoryMealsAdapter adapter;
    IngredientSearchpresenter presenter;
    List<MealDetails> meals = new ArrayList<>();
    List<MealDetails> mealsLetterList = new ArrayList<>();
    List<MealDetails> mealsNamesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_search);
        searchView = findViewById(R.id.searchViewingredient);
        recyclerIngredient = findViewById(R.id.ingredientList);
        ingredientName = findViewById(R.id.ingredientName);
        Intent intent = getIntent();
        String IntentIngredientName = intent.getStringExtra("ingredienName");

        ingredientName.setText("Meals of " + IntentIngredientName);
        recyclerIngredient.setHasFixedSize(true);
        GridLayoutManager manager2 = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerIngredient.setLayoutManager(manager2);
        presenter = new IngredientSearchpresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(this)
                , this)
                , this);
        presenter.getMealsByIngredient(IntentIngredientName);
        adapter = new CategoryMealsAdapter(this, meals , this );
        recyclerIngredient.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getMealsWithName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText))
                    presenter.getMealsByIngredient(IntentIngredientName);
                else
                    presenter.getMealsStartsWith(newText);
                return true;
            }
        });

    }

    @Override
    public void onClick(MealDetails mealDetails) {

    }

    @Override
    public void showIngredientMeals(List<MealDetails> meals) {
        adapter.setList(meals);
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
}