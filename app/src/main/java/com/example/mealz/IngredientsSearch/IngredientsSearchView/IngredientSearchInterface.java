package com.example.mealz.IngredientsSearch.IngredientsSearchView;

import com.example.mealz.model.MealDetails;

import java.util.List;

public interface IngredientSearchInterface {
    void showIngredientMeals(List<MealDetails> meals);
    void showMealsStartsWith(List<MealDetails> meals);
    void showMealsWithName(List<MealDetails> meals);
}
