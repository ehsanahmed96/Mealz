package com.example.mealz.MealDetails.MealDetailsPresenter;

import com.example.mealz.model.MealDetails;

public interface MealDetailsPresenterInterface {
    public void addToFav(MealDetails meal);
    public void getSpecificMeal(String id);
}
