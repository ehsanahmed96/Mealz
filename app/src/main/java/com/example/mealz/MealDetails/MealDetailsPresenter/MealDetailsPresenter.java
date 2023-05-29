package com.example.mealz.MealDetails.MealDetailsPresenter;

import com.example.mealz.MealDetails.MealDetailsView.MealDetailsInterface;
import com.example.mealz.Network.DetailsMealNetworkDelegate;
import com.example.mealz.model.MealDetails;
import com.example.mealz.model.MealResponse;
import com.example.mealz.model.Repository;
import com.example.mealz.model.WeekPlan;

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
    public void addToWeekPlan(WeekPlan plan) {
        repository.insertMealIntoWeek(plan);
    }

    @Override
    public void updateSat(String x, String id) {
        repository.updateSat(x, id);
    }

    @Override
    public void updateSun(String x, String id) {
        repository.updateSun(x, id);
    }

    @Override
    public void updateMon(String x, String id) {
        repository.updateMon(x, id);
    }

    @Override
    public void updateTues(String x, String id) {
        repository.updateTues(x, id);
    }

    @Override
    public void updateWed(String x, String id) {
        repository.updateWed(x, id);
    }

    @Override
    public void updateThurs(String x, String id) {
        repository.updateThurs(x, id);
    }

    @Override
    public void updateFri(String x, String id) {
        repository.updateFri(x, id);
    }


    @Override
    public void onSuccessFindingMeal(MealDetails meal) {
        mealDetailsInterface.showSpecificItem(meal);
    }

    @Override
    public void onFailureIngResult(String message) {

    }
}
