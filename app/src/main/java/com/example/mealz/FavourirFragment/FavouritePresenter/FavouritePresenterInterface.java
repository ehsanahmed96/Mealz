package com.example.mealz.FavourirFragment.FavouritePresenter;

import androidx.lifecycle.LiveData;

import com.example.mealz.model.MealDetails;

import java.util.List;

public interface FavouritePresenterInterface {
    public LiveData<List<MealDetails>> getFavourites();
    public void removeMeal(MealDetails mealDetails);
}
