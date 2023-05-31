package com.example.mealz.MealDetails.MealDetailsPresenter;

import androidx.lifecycle.LiveData;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.WeekPlan;

public interface MealDetailsPresenterInterface {
    public void addToFav(MealDetails meal);
    public void getSpecificMeal(String id);
    public void addToWeekPlan(WeekPlan plan);
    public LiveData<MealDetails> getOffMealDetail(String meal);
    public LiveData<WeekPlan> getOffMealPlan(String meal);
}
