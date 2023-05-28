package com.example.mealz.FavourirFragment.FavouritePresenter;

import androidx.lifecycle.LiveData;

import com.example.mealz.FavourirFragment.FavouriteView.FavouriteFragmentInterface;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.Repository;
import com.example.mealz.model.RepositoryInterface;

import java.util.List;

public class FavouritePresenter implements FavouritePresenterInterface {
    private RepositoryInterface repository;

    public FavouritePresenter(RepositoryInterface repositoryInterface ) {
        this.repository = repositoryInterface;
    }

    @Override
    public LiveData<List<MealDetails>> getFavourites() {
        return repository.getCachedMeals();
    }

    @Override
    public void removeMeal(MealDetails mealDetails) {
        repository.deleteMeal(mealDetails);
    }
}
