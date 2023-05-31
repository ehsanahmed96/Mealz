package com.example.mealz.Network;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.MealResponse;

public interface DetailsMealNetworkDelegate {
    public void onSuccessFindingMeal(MealDetails meal);
    public void onFailureIngResult(String message);
//public void onSuccessOfflineMealDetails(MealDetails meal);
}
