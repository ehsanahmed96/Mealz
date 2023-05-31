package com.example.mealz.CountrySearch.CountrySearchView;

import com.example.mealz.model.MealDetails;

import java.util.List;

public interface CountrySearchActivityInterface {
    void showCountryMeals(List<MealDetails> meals);
    void showMealsStartsWith(List<MealDetails> meals);
    void showMealsWithName(List<MealDetails> meals);
}
