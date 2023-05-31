package com.example.mealz.dp;

import androidx.lifecycle.LiveData;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface LocalSource {
    public void insertMealToFav(MealDetails mealDetails);
    public void delete(MealDetails mealDetails);
    public LiveData<List<MealDetails>> getCachedMeals();
    public void insertMealIntoWeek(WeekPlan meal);
    public void deleteMealFromPlan(WeekPlan meal);

    Observable<List<WeekPlan>> getSundayMeals();
    Observable<List<WeekPlan>>getMondayMeals();
    Observable<List<WeekPlan>>getTuesdayMeals();
    Observable<List<WeekPlan>>getWeddayMeals();
    Observable<List<WeekPlan>>getThursdayMeals();
    Observable<List<WeekPlan>>getSatdayMeals();
    Observable<List<WeekPlan>>getFridayMeals();

    LiveData<MealDetails> getOfflineMealDetail(String mealName);

    abstract LiveData<WeekPlan> getOfflineMealPlan(String mealName);
}
