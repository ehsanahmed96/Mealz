package com.example.mealz.WeekPlan.WeekPlanPresenter;

import com.example.mealz.WeekPlan.WeekPlanView.WeekPlanFragmentInterface;
import com.example.mealz.model.Repository;
import com.example.mealz.model.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class WeekPlanPresenter implements  WeekPlanPresenterInterface{
    Repository repository;
    WeekPlanFragmentInterface weekPlanFragmentInterface;

    public WeekPlanPresenter(Repository repository , WeekPlanFragmentInterface weekPlanFragmentInterface){
        this.repository = repository;
        this.weekPlanFragmentInterface = weekPlanFragmentInterface;

    }
    @Override
    public void deleteMeal(WeekPlan meal) {
        repository.deleteMealFromPlan(meal);

    }

    @Override
    public Observable<List<WeekPlan>> getFridayMeal() {return repository.getStoredFriMeals();}

    @Override
    public Observable<List<WeekPlan>> getSatdayMeal() {
        return repository.getStoredSatMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getSundayMeal() {
        return repository.getStoredSunMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getMondayMeal() {
        return repository.getStoredMonMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getTuesdayMeal() {
        return repository.getStoredTuesMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getThursdayMeal() {
        return repository.getStoredThursMeals();
    }

    @Override
    public Observable<List<WeekPlan>> getWeddayMeal() {
        return repository.getStoredWedMeals();
    }
}
