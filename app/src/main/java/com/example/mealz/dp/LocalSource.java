package com.example.mealz.dp;

import androidx.lifecycle.LiveData;

import com.example.mealz.model.MealDetails;

import java.util.List;

public interface LocalSource {
    public void insertMealToFav(MealDetails mealDetails);
    public void delete(MealDetails mealDetails);
    public LiveData<List<MealDetails>> getCachedMeals();
}
