package com.example.mealz.HomeFragment.View;

import com.example.mealz.model.Category;
import com.example.mealz.model.Country;
import com.example.mealz.model.MealDetails;

import java.util.List;

public interface HomeFragmentInterface {
    public void showCategory(List<Category> categoryList);

    public void showRandomMeals(List<MealDetails> mealDetails);
    public void showCountry(List <Country> countryList);
    public void addMealToFav(MealDetails mealDetails);
}

