package com.example.mealz.model;

import androidx.lifecycle.LiveData;

import com.example.mealz.Network.DetailsMealNetworkDelegate;
import com.example.mealz.Network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {
    public void getRandomMeal(NetworkDelegate networkDelegate);
    public void getCategory(NetworkDelegate networkDelegate);
    public void getCountry(NetworkDelegate networkDelegate);
    public void insertMealToFav(MealDetails mealDetails);
    public void deleteMeal(MealDetails mealDetails);
    public LiveData<List<MealDetails>> getCachedMeals();
    public void getSpecificMeal(DetailsMealNetworkDelegate detailsNetworkDelegate, String meal);
}
