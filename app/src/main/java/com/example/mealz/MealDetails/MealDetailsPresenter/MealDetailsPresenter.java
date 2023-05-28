package com.example.mealz.MealDetails.MealDetailsPresenter;

import com.example.mealz.MealDetails.MealDetailsView.MealDetailsInterface;
import com.example.mealz.Network.DetailsMealNetworkDelegate;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.MealResponse;
import com.example.mealz.model.Repository;

public class MealDetailsPresenter implements MealDetailsPresenterInterface, DetailsMealNetworkDelegate {
    private Repository repository;
    MealDetailsInterface mealDetailsInterface;

    public MealDetailsPresenter(Repository repository, MealDetailsInterface mealDetailsInterface) {
        this.repository = repository;
        this.mealDetailsInterface = mealDetailsInterface;
    }

    @Override
    public void addToFav(MealDetails meal) {
        repository.insertMealToFav(meal);
    }

    @Override
    public void getSpecificMeal(String id) {
        repository.getSpecificMeal(this, id);

    }



    @Override
    public void onSuccessFindingMeal(MealDetails meal) {
        mealDetailsInterface.showSpecificItem(meal);
    }

    @Override
    public void onFailureIngResult(String message) {

    }
}
