package com.example.mealz.CategorySearch.CategorySearchView;

import com.example.mealz.model.MealDetails;

import java.util.List;

public interface CategorySearchActivityInterface {
    void showCategoryMeals(List<MealDetails> meals);
    void showMealsStartsWith(List<MealDetails> meals);
    void showMealsWithName(List<MealDetails> meals);
}
