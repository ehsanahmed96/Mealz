package com.example.mealz.MealDetails.MealDetailsView;

import com.example.mealz.model.MealDetails;
import com.example.mealz.model.MealResponse;
import com.example.mealz.model.WeekPlan;

public interface MealDetailsInterface {
    void addMealToFav(MealDetails meal);
    void showSpecificItem(MealDetails meals);

    void addMealInFirebase(MealDetails mealDetail,String key);
    void addMealInWeekPlanFirebase(WeekPlan mealDetail, String key);
}
