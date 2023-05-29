package com.example.mealz.WeekPlan.WeekPlanPresenter;

import com.example.mealz.model.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface WeekPlanPresenterInterface {
    public void deleteMeal(WeekPlan meal);
    public Observable<List<WeekPlan>> getFridayMeal();
    public Observable<List<WeekPlan>>  getSatdayMeal();
    public Observable<List<WeekPlan>>  getSundayMeal();
    public Observable<List<WeekPlan>>  getMondayMeal();
    public Observable<List<WeekPlan>>  getTuesdayMeal();
    public Observable<List<WeekPlan>>  getThursdayMeal();
    public Observable<List<WeekPlan>>  getWeddayMeal();
}
