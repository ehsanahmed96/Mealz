package com.example.mealz.Network;

import com.example.mealz.model.MealDetails;

import java.util.List;

public interface FilterNetworkDelegate {
    public void onSuccessGetMealsByCountry(List<MealDetails> meals);
    public void onSuccessGetMealsByCategory(List<MealDetails> meals);
    public void onSuccessGetMealsByIngredients(List<MealDetails> meals);
    public void onSuccessGetMealsStartswith(List<MealDetails> meals);
    public void onSuccessGetMealWithName(List<MealDetails> meals);
}
